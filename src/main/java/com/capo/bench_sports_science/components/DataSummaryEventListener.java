package com.capo.bench_sports_science.components;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capo.bench_sports_science.domain.shared.ChannelEvents;
import com.capo.bench_sports_science.dto.GarminSummaryDto;
import com.capo.bench_sports_science.event.FileIngestionCompletedEvent;
import com.capo.bench_sports_science.repository.GarminAnalyticSummaryRepository;
import com.capo.bench_sports_science.service.SseService;

import jakarta.transaction.Transactional;

@Component
public class DataSummaryEventListener {
	
	private final GarminAnalyticSummaryRepository garminAnalyticsRepository;
	private final SseService sseService;
	
	public DataSummaryEventListener(GarminAnalyticSummaryRepository garminAnalyticsRepository,
			SseService sseService) {
		this.garminAnalyticsRepository = garminAnalyticsRepository;
		this.sseService=sseService;
	}
	
	@Async 
    @EventListener
    @Transactional
    public void handleIngestionCompleted(FileIngestionCompletedEvent event) {
        Long fileId = event.fileRegisterId();
        GarminSummaryDto garminSummaryDto = garminAnalyticsRepository.fetchActivitySummary(fileId);
        sseService.sendEventToChannel(ChannelEvents.CHANNEL_SUMMARY.value(),ChannelEvents.Events.EVENT_SUMMARY, garminSummaryDto);
    }

}
