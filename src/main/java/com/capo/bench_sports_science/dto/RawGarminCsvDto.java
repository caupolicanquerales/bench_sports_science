package com.capo.bench_sports_science.dto;

public record RawGarminCsvDto(String timeStamp,
		String latitud,
		String longitud,
		String distanceM,
		String speedMs,
		String heartRateBpm,
		String cadenceRpm,
		String powerW) {

}
