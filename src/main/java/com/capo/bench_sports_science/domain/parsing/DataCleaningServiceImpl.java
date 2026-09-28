package com.capo.bench_sports_science.domain.parsing;

import java.util.OptionalDouble;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.capo.bench_sports_science.domain.shared.SportsScienceConstants;

@Service
public class DataCleaningServiceImpl implements DataCleaningService {

	private static final Set<String> MISSING_MARKERS = Set.of("", "--", "0", "00:00", "0:00");
	private static final String TIME_SEPARATOR = ":";

	@Override
	public double parseTimeToSeconds(String value) {
		return parseToSeconds(value).orElse(Double.NaN);
	}

	@Override
	public OptionalDouble parseToSeconds(String value) {
		if (isMissing(value)) {
			return OptionalDouble.empty();
		}
		String trimmed = value.trim();
		String[] timeParts = trimmed.split(TIME_SEPARATOR);
		try {
			if (timeParts.length == 2) {
				return OptionalDouble.of(parseMinutesSeconds(timeParts));
			}
			if (timeParts.length == 3) {
				return OptionalDouble.of(parseHoursMinutesSeconds(timeParts));
			}
			return OptionalDouble.of(Double.parseDouble(trimmed));
		} catch (NumberFormatException exception) {
			return OptionalDouble.empty();
		}
	}

	@Override
	public double parseNumeric(String value) {
		return parseToNumeric(value).orElse(Double.NaN);
	}

	@Override
	public OptionalDouble parseToNumeric(String value) {
		if (isMissing(value)) {
			return OptionalDouble.empty();
		}
		try {
			return OptionalDouble.of(Double.parseDouble(value.trim()));
		} catch (NumberFormatException exception) {
			return OptionalDouble.empty();
		}
	}

	private static double parseMinutesSeconds(String[] timeParts) {
		double minutes = Double.parseDouble(timeParts[0]);
		double seconds = Double.parseDouble(timeParts[1]);
		return minutes * SportsScienceConstants.SECONDS_PER_MINUTE + seconds;
	}

	private static double parseHoursMinutesSeconds(String[] timeParts) {
		double hours = Double.parseDouble(timeParts[0]);
		double minutes = Double.parseDouble(timeParts[1]);
		double seconds = Double.parseDouble(timeParts[2]);
		return hours * SportsScienceConstants.SECONDS_PER_HOUR
				+ minutes * SportsScienceConstants.SECONDS_PER_MINUTE
				+ seconds;
	}

	private static boolean isMissing(String value) {
		return value == null || MISSING_MARKERS.contains(value.trim());
	}
}