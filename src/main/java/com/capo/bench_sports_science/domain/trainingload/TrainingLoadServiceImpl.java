package com.capo.bench_sports_science.domain.trainingload;

import org.springframework.stereotype.Service;

import com.capo.bench_sports_science.domain.shared.ParameterName;
import com.capo.bench_sports_science.domain.shared.SportsScienceConstants;

@Service
public class TrainingLoadServiceImpl implements TrainingLoadService {

	private static final double TRIMP_EXPONENT = 1.92;
	private static final double TRIMP_EXPONENTIAL_COEFFICIENT = 0.64;
	private static final double SECONDS_PER_HOUR = 3600.0;
	private static final double PERCENT_SCALE = 100.0;

	@Override
	public double heartRateReserveFraction(double averageHeartRateBpm, double restingHeartRateBpm, double maxHeartRateBpm) {
		requireHeartRateRange(restingHeartRateBpm, maxHeartRateBpm);
		requireValidHeartRate(averageHeartRateBpm, ParameterName.AVERAGE_HEART_RATE_BPM);
		return (averageHeartRateBpm - restingHeartRateBpm) / (maxHeartRateBpm - restingHeartRateBpm);
	}

	@Override
	public double trimp(double durationMinutes, double averageHeartRateBpm, double restingHeartRateBpm, double maxHeartRateBpm) {
		requirePositive(durationMinutes, ParameterName.DURATION_MINUTES);
		double heartRateReserveFraction = heartRateReserveFraction(averageHeartRateBpm, restingHeartRateBpm, maxHeartRateBpm);
		return durationMinutes
				* heartRateReserveFraction
				* TRIMP_EXPONENTIAL_COEFFICIENT
				* Math.exp(TRIMP_EXPONENT * heartRateReserveFraction);
	}

	@Override
	public double intensityFactorFromPace(double gapSecondsPerKm, double thresholdPaceSecondsPerKm) {
		requirePositive(gapSecondsPerKm, ParameterName.GAP_SECONDS_PER_KM);
		requirePositive(thresholdPaceSecondsPerKm, ParameterName.THRESHOLD_PACE_SECONDS_PER_KM);
		double gapSpeedMetersPerSecond = SportsScienceConstants.METERS_PER_KILOMETER / gapSecondsPerKm;
		double thresholdSpeedMetersPerSecond = SportsScienceConstants.METERS_PER_KILOMETER / thresholdPaceSecondsPerKm;
		return gapSpeedMetersPerSecond / thresholdSpeedMetersPerSecond;
	}

	@Override
	public double intensityFactorFromPower(double normalizedPowerWatts, double criticalPowerWatts) {
		requirePositive(normalizedPowerWatts, ParameterName.NORMALIZED_POWER_WATTS);
		requirePositive(criticalPowerWatts, ParameterName.CRITICAL_POWER_WATTS);
		return normalizedPowerWatts / criticalPowerWatts;
	}

	@Override
	public double runningTrainingStressScore(double durationSeconds, double intensityFactor) {
		requirePositive(durationSeconds, ParameterName.DURATION_SECONDS);
		requireNonNegative(intensityFactor, ParameterName.INTENSITY_FACTOR);
		return durationSeconds * intensityFactor * intensityFactor / SECONDS_PER_HOUR * PERCENT_SCALE;
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