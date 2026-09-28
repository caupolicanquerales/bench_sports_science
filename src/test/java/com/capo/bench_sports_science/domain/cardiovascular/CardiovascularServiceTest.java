package com.capo.bench_sports_science.domain.cardiovascular;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardiovascularServiceTest {

	private static final double DELTA = 1e-6;

	private CardiovascularService cardiovascularService;

	@BeforeEach
	void setUp() {
		cardiovascularService = new CardiovascularServiceImpl();
	}

	@Test
	void heartRateReservePercent() {
		assertEquals(59.25925925925925, cardiovascularService.heartRateReservePercent(130.0, 50.0, 185.0), DELTA);
	}

	@Test
	void heartRateReservePercentThrowsWhenRangeIsInvalid() {
		assertThrows(IllegalArgumentException.class,
				() -> cardiovascularService.heartRateReservePercent(130.0, 185.0, 50.0));
	}

	@Test
	void efficiencyFactorFromPace() {
		assertEquals(1.3333333333333333, cardiovascularService.efficiencyFactorFromPace(150.0, 300.0), DELTA);
	}

	@Test
	void efficiencyFactorFromPower() {
		assertEquals(1.6, cardiovascularService.efficiencyFactorFromPower(150.0, 240.0), DELTA);
	}

	@Test
	void aerobicDecouplingPercent() {
		assertEquals(7.142857142857128, cardiovascularService.aerobicDecouplingPercent(1.4, 1.3), DELTA);
	}

	@Test
	void aerobicDecouplingThrowsWhenFirstHalfIsZero() {
		assertThrows(IllegalArgumentException.class, () -> cardiovascularService.aerobicDecouplingPercent(0.0, 1.3));
	}
}