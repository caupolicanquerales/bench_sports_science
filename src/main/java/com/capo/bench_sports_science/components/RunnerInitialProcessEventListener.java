package com.capo.bench_sports_science.components;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capo.bench_sports_science.event.GarminRawFileEvent;
import com.capo.bench_sports_science.service.GarminJobRunnerService;

import jakarta.transaction.Transactional;

@Component
public class RunnerInitialProcessEventListener {
	
	private final GarminJobRunnerService garminJobRunnerService;
	
	public RunnerInitialProcessEventListener(GarminJobRunnerService garminJobRunnerService) {
		this.garminJobRunnerService= garminJobRunnerService;
	}
	
	@Async 
    @EventListener
    @Transactional
    public void handleChartsDataCompleted(GarminRawFileEvent event) throws Exception {
        String fileId = event.fileIdentifier();
        String fileName = event.fileName();
        String userId = event.userId();
        garminJobRunnerService.runIngestionJob(fileId, fileName, userId);
	}
}
