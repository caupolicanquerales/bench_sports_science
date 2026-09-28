package com.capo.bench_sports_science.domain.cardiovascular;

public interface CardiovascularService {

	double heartRateReservePercent(double averageHeartRateBpm, double restingHeartRateBpm, double maxHeartRateBpm);

	double efficiencyFactorFromPace(double averageHeartRateBpm, double paceSecondsPerKm);

	double efficiencyFactorFromPower(double averageHeartRateBpm, double averagePowerWatts);

	double aerobicDecouplingPercent(double firstHalfEfficiencyFactor, double secondHalfEfficiencyFactor);
}