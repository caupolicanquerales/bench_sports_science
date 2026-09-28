package com.capo.bench_sports_science.domain.shared;

public enum ParameterName {

	ASCENT_METERS(Keys.ASCENT_METERS),
	AVERAGE_HEART_RATE_BPM(Keys.AVERAGE_HEART_RATE_BPM),
	AVERAGE_POWER_WATTS(Keys.AVERAGE_POWER_WATTS),
	AVERAGE_WATTS_PER_KG(Keys.AVERAGE_WATTS_PER_KG),
	BODY_MASS_KG(Keys.BODY_MASS_KG),
	CRITICAL_POWER_WATTS(Keys.CRITICAL_POWER_WATTS),
	CADENCE_SPM(Keys.CADENCE_SPM),
	VALUE(Keys.VALUE),
	DESCENT_METERS(Keys.DESCENT_METERS),
	DISTANCE_KM(Keys.DISTANCE_KM),
	DURATION_MINUTES(Keys.DURATION_MINUTES),
	DURATION_SECONDS(Keys.DURATION_SECONDS),
	FLIGHT_TIME_SECONDS(Keys.FLIGHT_TIME_SECONDS),
	FORM_POWER_WATTS(Keys.FORM_POWER_WATTS),
	FIRST_HALF_EFFICIENCY_FACTOR(Keys.FIRST_HALF_EFFICIENCY_FACTOR),
	GAP_SECONDS_PER_KM(Keys.GAP_SECONDS_PER_KM),
	GRADIENT(Keys.GRADIENT),
	GROUND_CONTACT_TIME_MILLIS(Keys.GROUND_CONTACT_TIME_MILLIS),
	GROUND_CONTACT_TIME_SECONDS(Keys.GROUND_CONTACT_TIME_SECONDS),
	HEART_RATE_MAX_BPM(Keys.HEART_RATE_MAX_BPM),
	HEART_RATE_REST_BPM(Keys.HEART_RATE_REST_BPM),
	INTENSITY_FACTOR(Keys.INTENSITY_FACTOR),
	LEG_COMPRESSION_METERS(Keys.LEG_COMPRESSION_METERS),
	MAX_HEART_RATE_BPM(Keys.MAX_HEART_RATE_BPM),
	NORMALIZED_POWER_WATTS(Keys.NORMALIZED_POWER_WATTS),
	PACE_SECONDS_PER_KM(Keys.PACE_SECONDS_PER_KM),
	PEAK_FORCE_NEWTONS(Keys.PEAK_FORCE_NEWTONS),
	POWER_WATTS(Keys.POWER_WATTS),
	RESTING_HEART_RATE_BPM(Keys.RESTING_HEART_RATE_BPM),
	SECOND_HALF_EFFICIENCY_FACTOR(Keys.SECOND_HALF_EFFICIENCY_FACTOR),
	SPEED_METERS_PER_SECOND(Keys.SPEED_METERS_PER_SECOND),
	STRIDE_LENGTH_METERS(Keys.STRIDE_LENGTH_METERS),
	STRIDE_TIME_SECONDS(Keys.STRIDE_TIME_SECONDS),
	THRESHOLD_PACE_SECONDS_PER_KM(Keys.THRESHOLD_PACE_SECONDS_PER_KM),
	TOTAL_RUNNING_POWER_WATTS(Keys.TOTAL_RUNNING_POWER_WATTS),
	VERTICAL_OSCILLATION_CM(Keys.VERTICAL_OSCILLATION_CM);

	private final String value;

	ParameterName(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.value;
	}
	 
	public static final class Keys {
		public static final String ASCENT_METERS="ascentMeters";
		public static final String AVERAGE_HEART_RATE_BPM="averageHeartRateBpm";
		public static final String AVERAGE_POWER_WATTS="averagePowerWatts";
		public static final String AVERAGE_WATTS_PER_KG="averageWattsPerKg";
		public static final String BODY_MASS_KG="bodyMassKg";
		public static final String CRITICAL_POWER_WATTS="criticalPowerWatts";
		public static final String CADENCE_SPM="cadenceSpm";
		public static final String VALUE="value";
		public static final String DESCENT_METERS="descentMeters";
		public static final String DISTANCE_KM="distanceKm";
		public static final String DURATION_MINUTES="durationMinutes";
		public static final String DURATION_SECONDS="durationSeconds";
		public static final String FLIGHT_TIME_SECONDS="flightTimeSeconds";
		public static final String FORM_POWER_WATTS="formPowerWatts";
		public static final String FIRST_HALF_EFFICIENCY_FACTOR="firstHalfEfficiencyFactor";
		public static final String GAP_SECONDS_PER_KM="gapSecondsPerKm";
		public static final String GRADIENT="gradient";
		public static final String GROUND_CONTACT_TIME_MILLIS="groundContactTimeMillis";
		public static final String GROUND_CONTACT_TIME_SECONDS="groundContactTimeSeconds";
		public static final String HEART_RATE_MAX_BPM="heartRateMaxBpm";
		public static final String HEART_RATE_REST_BPM="heartRateRestBpm";
		public static final String INTENSITY_FACTOR="intensityFactor";
		public static final String LEG_COMPRESSION_METERS="legCompressionMeters";
		public static final String MAX_HEART_RATE_BPM="maxHeartRateBpm";
		public static final String NORMALIZED_POWER_WATTS="normalizedPowerWatts";
		public static final String PACE_SECONDS_PER_KM="paceSecondsPerKm";
		public static final String PEAK_FORCE_NEWTONS="peakForceNewtons";
		public static final String POWER_WATTS="powerWatts";
		public static final String RESTING_HEART_RATE_BPM="restingHeartRateBpm";
		public static final String SECOND_HALF_EFFICIENCY_FACTOR="secondHalfEfficiencyFactor";
		public static final String SPEED_METERS_PER_SECOND="speedMetersPerSecond";
		public static final String STRIDE_LENGTH_METERS="strideLengthMeters";
		public static final String STRIDE_TIME_SECONDS="strideTimeSeconds";
		public static final String THRESHOLD_PACE_SECONDS_PER_KM="thresholdPaceSecondsPerKm";
		public static final String TOTAL_RUNNING_POWER_WATTS="totalRunningPowerWatts";
		public static final String VERTICAL_OSCILLATION_CM="verticalOscillationCm";
    }
	
	public static final class TableNames {
		public static final String ASCENT_METERS="ASCENT_METERS";
		public static final String AVERAGE_HEART_RATE_BPM="AVERAGE_HEART_RATE_BPM";
		public static final String AVERAGE_POWER_WATTS="AVERAGE_POWER_WATTS";
		public static final String AVERAGE_WATTS_PER_KG="AVERAGE_WATTS_PER_KG";
		public static final String BODY_MASS_KG="BODY_MASS_KG";
		public static final String CRITICAL_POWER_WATTS="CRITICAL_POWER_WATTS";
		public static final String CADENCE_SPM="CADENCE_SPM";
		public static final String VALUE="VALUE";
		public static final String DESCENT_METERS="DESCENT_METERS";
		public static final String DISTANCE_KM="DISTANCE_KM";
		public static final String DURATION_MINUTES="DURATION_MINUTES";
		public static final String DURATION_SECONDS="DURATION_SECONDS";
		public static final String FLIGHT_TIME_SECONDS="FLIGHT_TIME_SECONDS";
		public static final String FORM_POWER_WATTS="FORM_POWER_WATTS";
		public static final String FIRST_HALF_EFFICIENCY_FACTOR="FIRST_HALF_EFFICIENCY_FACTOR";
		public static final String GAP_SECONDS_PER_KM="GAP_SECONDS_PER_KM";
		public static final String GRADIENT="GRADIENT";
		public static final String GROUND_CONTACT_TIME_MILLIS="GROUND_CONTACT_TIME_MILLIS";
		public static final String GROUND_CONTACT_TIME_SECONDS="GROUND_CONTACT_TIME_SECONDS";
		public static final String HEART_RATE_MAX_BPM="HEART_RATE_MAX_BPM";
		public static final String HEART_RATE_REST_BPM="HEART_RATE_REST_BPM";
		public static final String INTENSITY_FACTOR="INTENSITY_FACTOR";
		public static final String LEG_COMPRESSION_METERS="LEG_COMPRESSION_METERS";
		public static final String MAX_HEART_RATE_BPM="MAX_HEART_RATE_BPM";
		public static final String NORMALIZED_POWER_WATTS="NORMALIZED_POWER_WATTS";
		public static final String PACE_SECONDS_PER_KM="PACE_SECONDS_PER_KM";
		public static final String PEAK_FORCE_NEWTONS="PEAK_FORCE_NEWTONS";
		public static final String POWER_WATTS="POWER_WATTS";
		public static final String RESTING_HEART_RATE_BPM="RESTING_HEART_RATE_BPM";
		public static final String SECOND_HALF_EFFICIENCY_FACTOR="SECOND_HALF_EFFICIENCY_FACTOR";
		public static final String SPEED_METERS_PER_SECOND="SPEED_METERS_PER_SECOND";
		public static final String STRIDE_LENGTH_METERS="STRIDE_LENGTH_METERS";
		public static final String STRIDE_TIME_SECONDS="STRIDE_TIME_SECONDS";
		public static final String THRESHOLD_PACE_SECONDS_PER_KM="THRESHOLD_PACE_SECONDS_PER_KM";
		public static final String TOTAL_RUNNING_POWER_WATTS="TOTAL_RUNNING_POWER_WATTS";
		public static final String VERTICAL_OSCILLATION_CM="VERTICAL_OSCILLATION_CM";
	}
}
