package com.capo.bench_sports_science;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.capo.bench_sports_science.dto.GarminSummaryDto;
import com.capo.bench_sports_science.repository.GarminAnalyticSummaryRepository;

class GarminAnalyticSummaryRepositoryTest {

    @Test
    void shouldMapSummaryMetricsIncludingSpeedDurationAndPace() {
        Object[] result = new Object[] {
            150, 190, 250, 350, 90,
            10.5, 1800L, 5.71, 4200.0
        };

        GarminSummaryDto summary = GarminAnalyticSummaryRepository.mapSummaryResult(result);

        assertEquals(150, summary.avgHeartRate());
        assertEquals(190, summary.maxHeartRate());
        assertEquals(250, summary.avgPower());
        assertEquals(350, summary.maxPower());
        assertEquals(90, summary.avgCadence());
        assertEquals(10.5, summary.avgSpeedKmh());
        assertEquals(1800L, summary.totalTimeSeconds());
        assertEquals(5.71, summary.avgPaceMinPerKm());
        assertEquals(4200.0, summary.totalDistanceM());
    }
}
