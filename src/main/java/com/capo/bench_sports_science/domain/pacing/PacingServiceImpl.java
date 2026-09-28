package com.capo.bench_sports_science.domain.pacing;

import org.springframework.stereotype.Service;

import com.capo.bench_sports_science.domain.shared.ParameterName;
import com.capo.bench_sports_science.domain.shared.SportsScienceConstants;

@Service
public class PacingServiceImpl implements PacingService {

	@Override
	public double speedFromPace(double paceSecondsPerKm) {
		requirePositive(paceSecondsPerKm, ParameterName.PACE_SECONDS_PER_KM);
		return SportsScienceConstants.METERS_PER_KILOMETER / paceSecondsPerKm;
	}

	@Override
	public double speedKmhFromPace(double paceSecondsPerKm) {
		requirePositive(paceSecondsPerKm, ParameterName.PACE_SECONDS_PER_KM);
		double secondsPerHour = SportsScienceConstants.SECONDS_PER_MINUTE * SportsScienceConstants.SECONDS_PER_MINUTE;
		return secondsPerHour / paceSecondsPerKm;
	}

	@Override
	public double gradient(double ascentMeters, double descentMeters, double distanceKm) {
		requireNonNegative(ascentMeters, ParameterName.ASCENT_METERS);
		requireNonNegative(descentMeters, ParameterName.DESCENT_METERS);
		if (distanceKm <= 0.0) {
			return 0.0;
		}
		return (ascentMeters - descentMeters) / (distanceKm * SportsScienceConstants.METERS_PER_KILOMETER);
	}

	@Override
	public double minettiEnergyCost(double gradient) {
		return 155.4 * Math.pow(gradient, 5)
				- 30.4 * Math.pow(gradient, 4)
				- 43.3 * Math.pow(gradient, 3)
				+ 46.3 * Math.pow(gradient, 2)
				+ 19.5 * gradient
				+ SportsScienceConstants.MINETTI_FLAT_ENERGY_COST_J_PER_KG_PER_M;
	}

	@Override
	public double gradeAdjustedSpeed(double paceSecondsPerKm, double gradient) {
		double flatEnergyCost = SportsScienceConstants.MINETTI_FLAT_ENERGY_COST_J_PER_KG_PER_M;
		return speedFromPace(paceSecondsPerKm) * (minettiEnergyCost(gradient) / flatEnergyCost);
	}

	@Override
	public double gradeAdjustedPace(double paceSecondsPerKm, double gradient) {
		return SportsScienceConstants.METERS_PER_KILOMETER / gradeAdjustedSpeed(paceSecondsPerKm, gradient);
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