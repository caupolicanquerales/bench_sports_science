package com.capo.bench_sports_science.domain.mechanicalpower;

import java.util.OptionalDouble;

public interface MechanicalPowerService {

	double deriveBodyMassKg(double averagePowerWatts, double averageWattsPerKg);

	double deriveBodyMassKgOrFallback(OptionalDouble averagePowerWatts, OptionalDouble averageWattsPerKg);

	double costOfRunning(double powerWatts, double bodyMassKg, double speedMetersPerSecond);

	double formPowerRatio(double formPowerWatts, double totalRunningPowerWatts);
}