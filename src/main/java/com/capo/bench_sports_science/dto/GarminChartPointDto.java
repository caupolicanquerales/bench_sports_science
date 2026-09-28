package com.capo.bench_sports_science.dto;

import java.time.OffsetDateTime;

public record GarminChartPointDto(
		OffsetDateTime bucket,
	    Integer heartRate,
	    Integer power,
	    Integer cadence,
	    Double speedKmH) {

}
