package com.capo.bench_sports_science.components;

import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capo.bench_sports_science.domain.shared.ChannelEvents;
import com.capo.bench_sports_science.dto.GarminChartPointDto;
import com.capo.bench_sports_science.event.FileIngestionCompletedEvent;
import com.capo.bench_sports_science.repository.GarminAnalyticSummaryRepository;
import com.capo.bench_sports_science.service.SseService;

import jakarta.transaction.Transactional;

@Component
public class DataChartsEventListener {
	
	private final GarminAnalyticSummaryRepository garminAnalyticsRepository;
	private final SseService sseService;
	
	public DataChartsEventListener(GarminAnalyticSummaryRepository garminAnalyticsRepository,
			SseService sseService) {
		this.garminAnalyticsRepository = garminAnalyticsRepository;
		this.sseService = sseService;
	}
	
	@Async 
    @EventListener
    @Transactional
    public void handleChartsDataCompleted(FileIngestionCompletedEvent event) {
        Long fileId = event.fileRegisterId();
        List<GarminChartPointDto> chartData = garminAnalyticsRepository.fetchChartPoints(fileId);
        sseService.sendEventToChannel(ChannelEvents.CHANNEL_CHARTS.value(),ChannelEvents.Events.EVENT_CHARTS, chartData);
    }
}
