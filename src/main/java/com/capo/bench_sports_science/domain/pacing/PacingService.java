package com.capo.bench_sports_science.domain.pacing;

public interface PacingService {

	double speedFromPace(double paceSecondsPerKm);

	double speedKmhFromPace(double paceSecondsPerKm);

	double gradient(double ascentMeters, double descentMeters, double distanceKm);

	double minettiEnergyCost(double gradient);

	double gradeAdjustedSpeed(double paceSecondsPerKm, double gradient);

	double gradeAdjustedPace(double paceSecondsPerKm, double gradient);
}