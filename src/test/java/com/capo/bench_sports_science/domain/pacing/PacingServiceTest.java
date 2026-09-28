package com.capo.bench_sports_science.domain.pacing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PacingServiceTest {

	private static final double DELTA = 1e-6;

	private PacingService pacingService;

	@BeforeEach
	void setUp() {
		pacingService = new PacingServiceImpl();
	}

	@Test
	void speedFromPaceAtFiveMinutesPerKilometer() {
		assertEquals(3.3333333333333335, pacingService.speedFromPace(300.0), DELTA);
	}

	@Test
	void speedFromPaceThrowsWhenPaceIsNotPositive() {
		assertThrows(IllegalArgumentException.class, () -> pacingService.speedFromPace(0.0));
	}

	@Test
	void speedKmhFromPaceAtFiveMinutesPerKilometer() {
		assertEquals(12.0, pacingService.speedKmhFromPace(300.0), DELTA);
	}

	@Test
	void gradientWithAscentAndDescent() {
		assertEquals(0.01, pacingService.gradient(100.0, 50.0, 5.0), DELTA);
	}

	@Test
	void gradientReturnsZeroForZeroDistance() {
		assertEquals(0.0, pacingService.gradient(100.0, 50.0, 0.0), DELTA);
	}

	@Test
	void minettiEnergyCostAtFlat() {
		assertEquals(3.6, pacingService.minettiEnergyCost(0.0), DELTA);
	}

	@Test
	void minettiEnergyCostAtOnePercentGradient() {
		assertEquals(3.79958641154, pacingService.minettiEnergyCost(0.01), DELTA);
	}

	@Test
	void gradeAdjustedSpeedAtOnePercentGradient() {
		assertEquals(3.5181355662407405, pacingService.gradeAdjustedSpeed(300.0, 0.01), DELTA);
	}

	@Test
	void gradeAdjustedPaceAtOnePercentGradient() {
		assertEquals(284.24146289181726, pacingService.gradeAdjustedPace(300.0, 0.01), DELTA);
	}
}