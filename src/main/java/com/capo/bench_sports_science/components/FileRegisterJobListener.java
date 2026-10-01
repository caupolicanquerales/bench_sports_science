package com.capo.bench_sports_science.components;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.capo.bench_sports_science.event.FileIngestionCompletedEvent;
import com.capo.bench_sports_science.models.FileRegisterModel;
import com.capo.bench_sports_science.repository.FileRegisterRepository;

@Component
public class FileRegisterJobListener implements JobExecutionListener {

    private final FileRegisterRepository fileRegisterRepository;
    private final ApplicationEventPublisher eventPublisher;

    public FileRegisterJobListener(FileRegisterRepository fileRegisterRepository,
            ApplicationEventPublisher eventPublisher) {
        this.fileRegisterRepository = fileRegisterRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        String fileName = jobExecution.getJobParameters().getString("fileName");
        String userId = jobExecution.getJobParameters().getString("userId");

        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId is required to register the uploaded Garmin file");
        }

        FileRegisterModel fileRegister = new FileRegisterModel();
        fileRegister.setFileName(fileName);
        fileRegister.setUserId(userId);
        fileRegister = fileRegisterRepository.save(fileRegister);

        jobExecution.getExecutionContext().putLong("fileRegisterId", fileRegister.getId());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            Long fileRegisterId = jobExecution.getExecutionContext().getLong("fileRegisterId");
            eventPublisher.publishEvent(new FileIngestionCompletedEvent(fileRegisterId));
        }
    }
}
