package com.capo.bench_sports_science.service;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GarminJobRunnerService {
	
	@Value("${minio.bucket-name}")
    private String defaultBucket;
	
	private final JobOperator jobOperator;
	private final Job ingestGarminRawJob;

	public GarminJobRunnerService(JobOperator jobOperator, Job ingestGarminRawJob) {
		this.jobOperator = jobOperator;
		this.ingestGarminRawJob = ingestGarminRawJob;
	}

	public JobExecution runIngestionJob(String fileId, String fileName, String userId) throws Exception {
	        
        JobParameters parameters = new JobParametersBuilder()
        		.addString("bucketName", defaultBucket)
                .addString("fileId", fileId)
                .addString("objectName", fileId)
                .addString("fileName", fileName)
                .addString("userId", userId)
                .addLong("runTime", System.currentTimeMillis())
                .toJobParameters();

        return jobOperator.start(ingestGarminRawJob, parameters);
    }

}
