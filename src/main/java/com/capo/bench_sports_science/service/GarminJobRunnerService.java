package com.capo.bench_sports_science.service;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.stereotype.Service;

@Service
public class GarminJobRunnerService {

	private final JobOperator jobOperator;
	private final Job ingestGarminRawJob;

	public GarminJobRunnerService(JobOperator jobOperator, Job ingestGarminRawJob) {
		this.jobOperator = jobOperator;
		this.ingestGarminRawJob = ingestGarminRawJob;
	}

	public JobExecution runIngestionJob(String filePath, String fileName) throws Exception {
	        
        JobParameters parameters = new JobParametersBuilder()
                .addString("filePath", filePath)
                .addString("fileName", fileName)
                .addLong("runTime", System.currentTimeMillis()) // Ensures uniqueness
                .toJobParameters();

        return jobOperator.start(ingestGarminRawJob, parameters);
    }

}
