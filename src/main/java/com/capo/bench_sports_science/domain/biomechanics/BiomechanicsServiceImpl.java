package com.capo.bench_sports_science.domain.biomechanics;

import org.springframework.stereotype.Service;

import com.capo.bench_sports_science.domain.shared.ParameterName;
import com.capo.bench_sports_science.domain.shared.SportsScienceConstants;

@Service
public class BiomechanicsServiceImpl implements BiomechanicsService {

	@Override
	public double speedFromCadenceAndStrideLength(double cadenceSpm, double strideLengthMeters) {
		requirePositive(cadenceSpm, ParameterName.CADENCE_SPM);
		requirePositive(strideLengthMeters, ParameterName.STRIDE_LENGTH_METERS);
		return cadenceSpm / SportsScienceConstants.SECONDS_PER_MINUTE * strideLengthMeters;
	}

	@Override
	public double strideTimeSeconds(double cadenceSpm) {
		requirePositive(cadenceSpm, ParameterName.CADENCE_SPM);
		return 120.0 / cadenceSpm;
	}

	@Override
	public double dutyFactor(double cadenceSpm, double groundContactTimeMillis) {
		requirePositive(cadenceSpm, ParameterName.CADENCE_SPM);
		requirePositive(groundContactTimeMillis, ParameterName.GROUND_CONTACT_TIME_MILLIS);
		return groundContactTimeMillis * cadenceSpm / 120000.0;
	}

	@Override
	public double flightTimeSeconds(double strideTimeSeconds, double groundContactTimeSeconds) {
		requirePositive(strideTimeSeconds, ParameterName.STRIDE_TIME_SECONDS);
		requireNonNegative(groundContactTimeSeconds, ParameterName.GROUND_CONTACT_TIME_SECONDS);
		return strideTimeSeconds - groundContactTimeSeconds;
	}

	@Override
	public double peakGroundReactionForce(double bodyMassKg, double groundContactTimeSeconds, double flightTimeSeconds) {
		requirePositive(bodyMassKg, ParameterName.BODY_MASS_KG);
		requirePositive(groundContactTimeSeconds, ParameterName.GROUND_CONTACT_TIME_SECONDS);
		requireNonNegative(flightTimeSeconds, ParameterName.FLIGHT_TIME_SECONDS);
		double strideTimeSeconds = groundContactTimeSeconds + flightTimeSeconds;
		return bodyMassKg * SportsScienceConstants.GRAVITY_MS2 * Math.PI / 2.0
				* (strideTimeSeconds / groundContactTimeSeconds);
	}

	@Override
	public double verticalStiffnessKnPerM(double peakForceNewtons, double verticalOscillationCm) {
		requirePositive(peakForceNewtons, ParameterName.PEAK_FORCE_NEWTONS);
		requirePositive(verticalOscillationCm, ParameterName.VERTICAL_OSCILLATION_CM);
		return peakForceNewtons / (verticalOscillationCm * 0.01) / 1000.0;
	}

	@Override
	public double legStiffnessKnPerM(double peakForceNewtons, double legCompressionMeters) {
		requirePositive(peakForceNewtons, ParameterName.PEAK_FORCE_NEWTONS);
		requirePositive(legCompressionMeters, ParameterName.LEG_COMPRESSION_METERS);
		return peakForceNewtons / legCompressionMeters / 1000.0;
	}

	@Override
	public double verticalRatioPercent(double verticalOscillationCm, double strideLengthMeters) {
		requirePositive(verticalOscillationCm, ParameterName.VERTICAL_OSCILLATION_CM);
		requirePositive(strideLengthMeters, ParameterName.STRIDE_LENGTH_METERS);
		return verticalOscillationCm / (strideLengthMeters * 100.0) * 100.0;
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