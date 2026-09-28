package com.capo.bench_sports_science.domain.mechanicalpower;

import java.util.OptionalDouble;

import org.springframework.stereotype.Service;

import com.capo.bench_sports_science.domain.shared.ParameterName;
import com.capo.bench_sports_science.domain.shared.SportsScienceConstants;

@Service
public class MechanicalPowerServiceImpl implements MechanicalPowerService {

	@Override
	public double deriveBodyMassKg(double averagePowerWatts, double averageWattsPerKg) {
		requirePositive(averagePowerWatts, ParameterName.AVERAGE_POWER_WATTS);
		requirePositive(averageWattsPerKg, ParameterName.AVERAGE_WATTS_PER_KG);
		return averagePowerWatts / averageWattsPerKg;
	}

	@Override
	public double deriveBodyMassKgOrFallback(OptionalDouble averagePowerWatts, OptionalDouble averageWattsPerKg) {
		if (averagePowerWatts.isPresent() && averageWattsPerKg.isPresent()
				&& averageWattsPerKg.getAsDouble() > 0.0) {
			return averagePowerWatts.getAsDouble() / averageWattsPerKg.getAsDouble();
		}
		return SportsScienceConstants.DEFAULT_MASS_KG;
	}

	@Override
	public double costOfRunning(double powerWatts, double bodyMassKg, double speedMetersPerSecond) {
		requirePositive(powerWatts, ParameterName.POWER_WATTS);
		requirePositive(bodyMassKg, ParameterName.BODY_MASS_KG);
		requirePositive(speedMetersPerSecond, ParameterName.SPEED_METERS_PER_SECOND);
		return powerWatts / (bodyMassKg * speedMetersPerSecond);
	}

	@Override
	public double formPowerRatio(double formPowerWatts, double totalRunningPowerWatts) {
		requireNonNegative(formPowerWatts, ParameterName.FORM_POWER_WATTS);
		requirePositive(totalRunningPowerWatts, ParameterName.TOTAL_RUNNING_POWER_WATTS);
		return formPowerWatts / totalRunningPowerWatts;
	}

	private static void requirePositive(double value, ParameterName parameterName) {
		if (value <= 0.0) {
			throw new IllegalArgumentException(parameterName.value() + " must be positive");
		}
	}

	private static void requireNonNegative(double value, ParameterName parameterName) {
		if (value < 0.0) {
			throw new IllegalArgumentException(parameterName.value() + " must be non-negative");
		}
	}
}