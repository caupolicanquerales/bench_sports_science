package com.capo.bench_sports_science.domain.biomechanics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BiomechanicsServiceTest {

	private static final double DELTA = 1e-6;

	private BiomechanicsService biomechanicsService;

	@BeforeEach
	void setUp() {
		biomechanicsService = new BiomechanicsServiceImpl();
	}

	@Test
	void speedFromCadenceAndStrideLength() {
		assertEquals(3.33, biomechanicsService.speedFromCadenceAndStrideLength(180.0, 1.11), DELTA);
	}

	@Test
	void strideTimeSecondsAtOneHundredEightySpm() {
		assertEquals(0.6666666666666666, biomechanicsService.strideTimeSeconds(180.0), DELTA);
	}

	@Test
	void dutyFactorAtTwoHundredTwentyMillisContact() {
		assertEquals(0.33, biomechanicsService.dutyFactor(180.0, 220.0), DELTA);
	}

	@Test
	void flightTimeSeconds() {
		assertEquals(0.44666666666666666,
				biomechanicsService.flightTimeSeconds(0.6666666666666666, 0.22), DELTA);
	}

	@Test
	void peakGroundReactionForce() {
		assertEquals(3502.1618104222575,
				biomechanicsService.peakGroundReactionForce(75.0, 0.22, 0.44666666666666666), DELTA);
	}

	@Test
	void verticalStiffnessKnPerM() {
		assertEquals(50.03088300603224, biomechanicsService.verticalStiffnessKnPerM(3502.1618104222575, 7.0), DELTA);
	}

	@Test
	void legStiffnessKnPerM() {
		assertEquals(70.04323620844515, biomechanicsService.legStiffnessKnPerM(3502.1618104222575, 0.05), DELTA);
	}

	@Test
	void verticalRatioPercent() {
		assertEquals(6.306306306306306, biomechanicsService.verticalRatioPercent(7.0, 1.11), DELTA);
	}

	@Test
	void verticalStiffnessThrowsWhenOscillationIsZero() {
		assertThrows(IllegalArgumentException.class, () -> biomechanicsService.verticalStiffnessKnPerM(3502.16, 0.0));
	}
}