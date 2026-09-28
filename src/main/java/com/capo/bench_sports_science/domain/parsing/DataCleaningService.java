package com.capo.bench_sports_science.domain.parsing;

import java.util.OptionalDouble;

public interface DataCleaningService {

	double parseTimeToSeconds(String value);

	OptionalDouble parseToSeconds(String value);

	double parseNumeric(String value);

	OptionalDouble parseToNumeric(String value);
}