package com.capo.bench_sports_science.domain.shared;

public enum ChannelEvents {
	
	CHANNEL_SUMMARY(Keys.CHANNEL_SUMMARY),
	CHANNEL_CHARTS(Keys.CHANNEL_CHARTS),
	CHANNEL_GPS(Keys.CHANNEL_GPS),
	CHANNEL_FILE(Keys.CHANNEL_FILE);

	private final String value;

	ChannelEvents(String value) {
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
		public static final String CHANNEL_SUMMARY="dataSummary";
		public static final String CHANNEL_CHARTS="dataCharts";
		public static final String CHANNEL_GPS="dataGps";
		public static final String CHANNEL_FILE="dataFile";

    }
	
	public static final class Events {
		public static final String EVENT_SUMMARY="event-garmin-summary";
		public static final String EVENT_CHARTS="event-garmin-charts";
		public static final String EVENT_GPS="event-garmin-gps";
		public static final String EVENT_FILE="event-garmin-file";
	}

}
