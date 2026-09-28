package com.capo.bench_sports_science.domain.trainingload;

public interface TrainingLoadService {

	double heartRateReserveFraction(double averageHeartRateBpm, double restingHeartRateBpm, double maxHeartRateBpm);

	double trimp(double durationMinutes, double averageHeartRateBpm, double restingHeartRateBpm, double maxHeartRateBpm);

	double intensityFactorFromPace(double gapSecondsPerKm, double thresholdPaceSecondsPerKm);

	double intensityFactorFromPower(double normalizedPowerWatts, double criticalPowerWatts);

	double runningTrainingStressScore(double durationSeconds, double intensityFactor);
}