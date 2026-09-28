package com.capo.bench_sports_science.domain.shared;

public record AthleteParameters(
		double heartRateRestBpm,
		double heartRateMaxBpm,
		double thresholdPaceSecondsPerKm,
		double bodyMassKg) {

	public AthleteParameters {
		if (heartRateRestBpm <= 0.0) {
			throw new IllegalArgumentException(ParameterName.HEART_RATE_REST_BPM.value() + " must be positive");
		}
		if (heartRateMaxBpm <= heartRateRestBpm) {
			throw new IllegalArgumentException(ParameterName.HEART_RATE_MAX_BPM.value() + " must be greater than "
					+ ParameterName.HEART_RATE_REST_BPM.value());
		}
		if (thresholdPaceSecondsPerKm <= 0.0) {
			throw new IllegalArgumentException(ParameterName.THRESHOLD_PACE_SECONDS_PER_KM.value() + " must be positive");
		}
		if (bodyMassKg <= 0.0) {
			throw new IllegalArgumentException(ParameterName.BODY_MASS_KG.value() + " must be positive");
		}
	}

	public static AthleteParameters defaults() {
		return new AthleteParameters(
				SportsScienceConstants.DEFAULT_HR_REST_BPM,
				SportsScienceConstants.DEFAULT_HR_MAX_BPM,
				SportsScienceConstants.DEFAULT_THRESHOLD_PACE_SECONDS_PER_KM,
				SportsScienceConstants.DEFAULT_MASS_KG);
	}
}