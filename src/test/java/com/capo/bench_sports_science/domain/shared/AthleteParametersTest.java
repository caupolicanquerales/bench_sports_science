package com.capo.bench_sports_science.domain.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AthleteParametersTest {

	private static final double DELTA = 1e-6;

	@Test
	void defaultsFollowGarminSpec() {
		AthleteParameters athleteParameters = AthleteParameters.defaults();
		assertEquals(50.0, athleteParameters.heartRateRestBpm(), DELTA);
		assertEquals(185.0, athleteParameters.heartRateMaxBpm(), DELTA);
		assertEquals(300.0, athleteParameters.thresholdPaceSecondsPerKm(), DELTA);
		assertEquals(75.0, athleteParameters.bodyMassKg(), DELTA);
	}

	@Test
	void rejectsMaxHeartRateNotAboveResting() {
		assertThrows(IllegalArgumentException.class,
				() -> new AthleteParameters(60.0, 60.0, 300.0, 75.0));
	}

	@Test
	void rejectsNonPositiveBodyMass() {
		assertThrows(IllegalArgumentException.class,
				() -> new AthleteParameters(50.0, 185.0, 300.0, 0.0));
	}
}