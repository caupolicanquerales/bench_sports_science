package com.capo.bench_sports_science.configurations;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.database.JpaItemWriter;
import org.springframework.batch.infrastructure.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.batch.autoconfigure.BatchTaskExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.capo.bench_sports_science.components.FileRegisterJobListener;
import com.capo.bench_sports_science.components.RawGarminItemProcessor;
import com.capo.bench_sports_science.domain.shared.RawParameterGarminName;
import com.capo.bench_sports_science.dto.RawGarminCsvDto;
import com.capo.bench_sports_science.models.RawGarminRegisterModel;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class GarminBatchConfig {
	
	private static final int CHUNK_SIZE = 500;
	
	
	@Bean
	@BatchTaskExecutor
	public VirtualThreadTaskExecutor garminBatchTaskExecutor() {
		return new VirtualThreadTaskExecutor("garmin-batch-vt-");
	}
	
	
	@Bean
    @StepScope
    public FlatFileItemReader<RawGarminCsvDto> csvReader(@Value("#{jobParameters['filePath']}") String filePath) {
        return new FlatFileItemReaderBuilder<RawGarminCsvDto>()
                .name("rawGarminCsvReader")
                .resource(new FileSystemResource(filePath))
                .linesToSkip(1)
                .delimited()
                .names(
                        RawParameterGarminName.Keys.TIME_STAMP,
                        RawParameterGarminName.Keys.LATITUD,
                        RawParameterGarminName.Keys.LONGITUD,
                        RawParameterGarminName.Keys.DISTANCE_M,
                        RawParameterGarminName.Keys.SPEED_MS,
                        RawParameterGarminName.Keys.HEART_RATE_BPM,
                        RawParameterGarminName.Keys.CADENCE_RPM,
                        RawParameterGarminName.Keys.POWER_W)
                .targetType(RawGarminCsvDto.class)
                .build();
    }

	@Bean
    public JpaItemWriter<RawGarminRegisterModel> jpaWriter(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<RawGarminRegisterModel>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

	@Bean
    public Step processRawGarminCsvStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            FlatFileItemReader<RawGarminCsvDto> csvReader,
            RawGarminItemProcessor processor,
            JpaItemWriter<RawGarminRegisterModel> jpaWriter) {

        return new StepBuilder("processRawGarminCsvStep", jobRepository)
                .<RawGarminCsvDto, RawGarminRegisterModel>chunk(CHUNK_SIZE)
                .transactionManager(transactionManager)
                .reader(csvReader)
                .processor(processor)
                .writer(jpaWriter)
                .build();
    }
	
	@Bean
    public Job ingestGarminRawJob(
            JobRepository jobRepository,
            Step processRawGarminCsvStep,
            FileRegisterJobListener jobListener) {

        return new JobBuilder("ingestGarminRawJob", jobRepository)
                .listener(jobListener)
                .start(processRawGarminCsvStep)
                .build();
    }
	
}
