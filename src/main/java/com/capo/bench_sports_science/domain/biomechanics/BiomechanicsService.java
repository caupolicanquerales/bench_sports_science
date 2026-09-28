package com.capo.bench_sports_science.domain.biomechanics;

public interface BiomechanicsService {

	double speedFromCadenceAndStrideLength(double cadenceSpm, double strideLengthMeters);

	double strideTimeSeconds(double cadenceSpm);

	double dutyFactor(double cadenceSpm, double groundContactTimeMillis);

	double flightTimeSeconds(double strideTimeSeconds, double groundContactTimeSeconds);

	double peakGroundReactionForce(double bodyMassKg, double groundContactTimeSeconds, double flightTimeSeconds);

	double verticalStiffnessKnPerM(double peakForceNewtons, double verticalOscillationCm);

	double legStiffnessKnPerM(double peakForceNewtons, double legCompressionMeters);

	double verticalRatioPercent(double verticalOscillationCm, double strideLengthMeters);
}