package com.capo.bench_sports_science.domain.cardiovascular;

import org.springframework.stereotype.Service;

import com.capo.bench_sports_science.domain.shared.ParameterName;
import com.capo.bench_sports_science.domain.shared.SportsScienceConstants;

@Service
public class CardiovascularServiceImpl implements CardiovascularService {

	private static final double PERCENT = 100.0;

	@Override
	public double heartRateReservePercent(double averageHeartRateBpm, double restingHeartRateBpm, double maxHeartRateBpm) {
		requireHeartRateRange(restingHeartRateBpm, maxHeartRateBpm);
		requireValidHeartRate(averageHeartRateBpm, ParameterName.AVERAGE_HEART_RATE_BPM);
		return (averageHeartRateBpm - restingHeartRateBpm)
				/ (maxHeartRateBpm - restingHeartRateBpm) * PERCENT;
	}

	@Override
	public double efficiencyFactorFromPace(double averageHeartRateBpm, double paceSecondsPerKm) {
		requireValidHeartRate(averageHeartRateBpm, ParameterName.AVERAGE_HEART_RATE_BPM);
		requirePositive(paceSecondsPerKm, ParameterName.PACE_SECONDS_PER_KM);
		double speedMetersPerSecond = SportsScienceConstants.METERS_PER_KILOMETER / paceSecondsPerKm;
		return speedMetersPerSecond * SportsScienceConstants.SECONDS_PER_MINUTE / averageHeartRateBpm;
	}

	@Override
	public double efficiencyFactorFromPower(double averageHeartRateBpm, double averagePowerWatts) {
		requireValidHeartRate(averageHeartRateBpm, ParameterName.AVERAGE_HEART_RATE_BPM);
		requirePositive(averagePowerWatts, ParameterName.AVERAGE_POWER_WATTS);
		return averagePowerWatts / averageHeartRateBpm;
	}

	@Override
	public double aerobicDecouplingPercent(double firstHalfEfficiencyFactor, double secondHalfEfficiencyFactor) {
		requirePositive(firstHalfEfficiencyFactor, ParameterName.FIRST_HALF_EFFICIENCY_FACTOR);
		requireNonNegative(secondHalfEfficiencyFactor, ParameterName.SECOND_HALF_EFFICIENCY_FACTOR);
		return (1.0 - secondHalfEfficiencyFactor / firstHalfEfficiencyFactor) * PERCENT;
	}

	private static void requireHeartRateRange(double restingHeartRateBpm, double maxHeartRateBpm) {
		requireValidHeartRate(restingHeartRateBpm, ParameterName.RESTING_HEART_RATE_BPM);
		requireValidHeartRate(maxHeartRateBpm, ParameterName.MAX_HEART_RATE_BPM);
		if (maxHeartRateBpm <= restingHeartRateBpm) {
			throw new IllegalArgumentException(ParameterName.MAX_HEART_RATE_BPM.value() + " must be greater than "
					+ ParameterName.RESTING_HEART_RATE_BPM.value());
		}
	}

	private static void requireValidHeartRate(double value, ParameterName parameterName) {
		if (value <= 0.0) {
			throw new IllegalArgumentException(parameterName.value() + " must be positive");
		}
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