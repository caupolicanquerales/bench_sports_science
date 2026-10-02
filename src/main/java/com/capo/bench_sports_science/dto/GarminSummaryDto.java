package com.capo.bench_sports_science.dto;

public record GarminSummaryDto(
		Integer avgHeartRate,
		Integer maxHeartRate,
		Integer avgPower,
		Integer maxPower,
		Integer avgCadence,
		Double avgSpeedKmh,
		Long totalTimeSeconds,
		Double avgPaceMinPerKm,
		Double totalDistanceM) {
}
