package com.capo.bench_sports_science.domain.parsing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.OptionalDouble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataCleaningServiceTest {

	private static final double DELTA = 1e-6;

	private DataCleaningService dataCleaningService;

	@BeforeEach
	void setUp() {
		dataCleaningService = new DataCleaningServiceImpl();
	}

	@Test
	void parsesMinutesAndSeconds() {
		assertEquals(65.0, dataCleaningService.parseTimeToSeconds("1:05"), DELTA);
	}

	@Test
	void parsesMinutesSecondsWithFraction() {
		assertEquals(65.5, dataCleaningService.parseTimeToSeconds("1:05.5"), DELTA);
	}

	@Test
	void parsesHoursMinutesSeconds() {
		assertEquals(3630.0, dataCleaningService.parseTimeToSeconds("1:00:30"), DELTA);
	}

	@Test
	void parsesNumericString() {
		assertEquals(300.0, dataCleaningService.parseTimeToSeconds("300"), DELTA);
	}

	@Test
	void returnsEmptyForMissingMarkers() {
		assertFalse(dataCleaningService.parseToSeconds("--").isPresent());
		assertFalse(dataCleaningService.parseToSeconds("0").isPresent());
		assertFalse(dataCleaningService.parseToSeconds("00:00").isPresent());
		assertFalse(dataCleaningService.parseToSeconds("0:00").isPresent());
		assertFalse(dataCleaningService.parseToSeconds("").isPresent());
		assertFalse(dataCleaningService.parseToSeconds(null).isPresent());
	}

	@Test
	void returnsNanForMissingMarkers() {
		assertTrue(Double.isNaN(dataCleaningService.parseTimeToSeconds("--")));
	}

	@Test
	void parsesNumericPower() {
		assertEquals(300.0, dataCleaningService.parseNumeric("300"), DELTA);
	}

	@Test
	void numericPowerReturnsEmptyForMissing() {
		OptionalDouble parsed = dataCleaningService.parseToNumeric("--");
		assertFalse(parsed.isPresent());
	}

	@Test
	void numericPowerReturnsEmptyForNonNumeric() {
		OptionalDouble parsed = dataCleaningService.parseToNumeric("abc");
		assertFalse(parsed.isPresent());
	}
}