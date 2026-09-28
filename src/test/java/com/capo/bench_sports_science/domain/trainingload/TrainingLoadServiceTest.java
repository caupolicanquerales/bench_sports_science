package com.capo.bench_sports_science.domain.trainingload;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainingLoadServiceTest {

	private static final double DELTA = 1e-6;

	private TrainingLoadService trainingLoadService;

	@BeforeEach
	void setUp() {
		trainingLoadService = new TrainingLoadServiceImpl();
	}

	@Test
	void heartRateReserveFraction() {
		assertEquals(0.5925925925925926, trainingLoadService.heartRateReserveFraction(130.0, 50.0, 185.0), DELTA);
	}

	@Test
	void trimp() {
		assertEquals(35.49670634087951, trainingLoadService.trimp(30.0, 130.0, 50.0, 185.0), DELTA);
	}

	@Test
	void intensityFactorFromPace() {
		assertEquals(1.0714285714285714, trainingLoadService.intensityFactorFromPace(280.0, 300.0), DELTA);
	}

	@Test
	void intensityFactorFromPower() {
		assertEquals(0.8666666666666667, trainingLoadService.intensityFactorFromPower(260.0, 300.0), DELTA);
	}

	@Test
	void runningTrainingStressScoreForOneHourAtZeroPointNine() {
		assertEquals(81.0, trainingLoadService.runningTrainingStressScore(3600.0, 0.9), DELTA);
	}

	@Test
	void runningTrainingStressScoreForThirtyMinutesAtZeroPointNine() {
		assertEquals(40.5, trainingLoadService.runningTrainingStressScore(1800.0, 0.9), DELTA);
	}

	@Test
	void runningTrainingStressScoreThrowsWhenIntensityFactorIsNegative() {
		assertThrows(IllegalArgumentException.class, () -> trainingLoadService.runningTrainingStressScore(3600.0, -0.5));
	}
}