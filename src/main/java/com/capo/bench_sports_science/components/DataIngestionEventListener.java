package com.capo.bench_sports_science.components;

import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capo.bench_sports_science.dto.GarminChartPointDto;
import com.capo.bench_sports_science.dto.GarminSummaryDto;
import com.capo.bench_sports_science.event.FileIngestionCompletedEvent;
import com.capo.bench_sports_science.repository.GarminAnalyticSummaryRepository;

import jakarta.transaction.Transactional;

@Component
public class DataIngestionEventListener {
	
	private final GarminAnalyticSummaryRepository garminAnalyticsRepository;
	
	public DataIngestionEventListener(GarminAnalyticSummaryRepository garminAnalyticsRepository) {
		this.garminAnalyticsRepository = garminAnalyticsRepository;
	}
	
	@Async 
    @EventListener
    @Transactional
    public void handleIngestionCompleted(FileIngestionCompletedEvent event) {
        Long fileId = event.fileRegisterId();
        GarminSummaryDto garminSummaryDto = garminAnalyticsRepository.fetchActivitySummary(fileId);
        List<GarminChartPointDto> chartData = garminAnalyticsRepository.fetchChartPoints(fileId);
        

        System.out.println("<-----------   SE EJECUTO EL PROCESO BATCH  ------------>");
        System.out.println(garminSummaryDto.avgCadence()+" "+garminSummaryDto.avgHeartRate());
        // 2. Broadcast summary event to all connected SSE clients
        //sseService.sendEventToClients("garmin-ingested-event", summary);
    }

}
