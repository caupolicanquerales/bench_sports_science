package com.capo.bench_sports_science.domain.mechanicalpower;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.OptionalDouble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MechanicalPowerServiceTest {

	private static final double DELTA = 1e-6;

	private MechanicalPowerService mechanicalPowerService;

	@BeforeEach
	void setUp() {
		mechanicalPowerService = new MechanicalPowerServiceImpl();
	}

	@Test
	void deriveBodyMassKg() {
		assertEquals(75.0, mechanicalPowerService.deriveBodyMassKg(300.0, 4.0), DELTA);
	}

	@Test
	void deriveBodyMassKgOrFallbackUsesDefaultsWhenMissing() {
		assertEquals(75.0, mechanicalPowerService.deriveBodyMassKgOrFallback(OptionalDouble.empty(), OptionalDouble.of(4.0)),
				DELTA);
	}

	@Test
	void deriveBodyMassKgOrFallbackComputesWhenBothPresent() {
		assertEquals(75.0, mechanicalPowerService.deriveBodyMassKgOrFallback(OptionalDouble.of(300.0), OptionalDouble.of(4.0)),
				DELTA);
	}

	@Test
	void costOfRunning() {
		assertEquals(1.2, mechanicalPowerService.costOfRunning(300.0, 75.0, 3.3333333333333335), DELTA);
	}

	@Test
	void costOfRunningThrowsWhenSpeedIsZero() {
		assertThrows(IllegalArgumentException.class, () -> mechanicalPowerService.costOfRunning(300.0, 75.0, 0.0));
	}

	@Test
	void formPowerRatio() {
		assertEquals(0.11666666666666667, mechanicalPowerService.formPowerRatio(35.0, 300.0), DELTA);
	}

	@Test
	void formPowerRatioThrowsWhenTotalPowerIsZero() {
		assertThrows(IllegalArgumentException.class, () -> mechanicalPowerService.formPowerRatio(35.0, 0.0));
	}
}