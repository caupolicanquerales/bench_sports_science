package com.capo.bench_sports_science.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = "spring.batch.job.enabled=false")
@ActiveProfiles("local")
public class GarminJobRunnerServiceTest {

	@Autowired
	GarminJobRunnerService garminJobRunnerService;

	@Test
	void runIngestionJob() throws Exception {
		String path= "/home/capo/Descargas/capo_entrenamiento.csv";
		JobExecution job= garminJobRunnerService.runIngestionJob(path, "capo_entrenamiento.csv");

		awaitTermination(job);

		assertEquals(BatchStatus.COMPLETED, job.getStatus(), () -> "Job failed: " + job.getAllFailureExceptions());
	}

	private static void awaitTermination(JobExecution jobExecution) throws InterruptedException {
		while (jobExecution.isRunning()) {
			Thread.sleep(500);
		}
	}

}
