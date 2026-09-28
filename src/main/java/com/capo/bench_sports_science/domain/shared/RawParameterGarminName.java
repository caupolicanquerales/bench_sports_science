package com.capo.bench_sports_science.domain.shared;

public enum RawParameterGarminName {
	
	TIME_STAMP(Keys.TIME_STAMP),
	LATITUD(Keys.LATITUD),
	LONGITUD(Keys.LONGITUD),
	DISTANCE_M(Keys.DISTANCE_M),
	SPEED_MS(Keys.SPEED_MS),
	HEART_RATE_BPM(Keys.HEART_RATE_BPM),
	CADENCE_RPM(Keys.CADENCE_RPM),
	POWER_W(Keys.POWER_W);

	private final String value;

	RawParameterGarminName(String value) {
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
		public static final String TIME_STAMP="timeStamp";
		public static final String LATITUD="latitud";
		public static final String LONGITUD="longitud";
		public static final String DISTANCE_M="distanceM";
		public static final String SPEED_MS="speedMs";
		public static final String HEART_RATE_BPM="heartRateBpm";
		public static final String CADENCE_RPM="cadenceRpm";
		public static final String POWER_W="powerW";
	
    }
	
	public static final class TableNames {
		public static final String TIME_STAMP="TIME_STAMP";
		public static final String LATITUD="LATITUD";
		public static final String LONGITUD="LONGITUD";
		public static final String DISTANCE_M="DISTANCE_M";
		public static final String SPEED_MS="SPEED_MS";
		public static final String HEART_RATE_BPM="HEART_RATE_BPM";
		public static final String CADENCE_RPM="CADENCE_RPM";
		public static final String POWER_W="POWER_W";
	}
}
