package com.capo.bench_sports_science.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.capo.bench_sports_science.domain.shared.ParameterName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="GARMIN_REGISTERS")
@Getter
@Setter
@NoArgsConstructor
public class GarminRegisterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FILE_REGISTER_ID", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private FileRegisterModel fileRegister;

	@Column(name = ParameterName.TableNames.ASCENT_METERS, nullable = false)
	private String ascentMeters;

	@Column(name = ParameterName.TableNames.AVERAGE_HEART_RATE_BPM, nullable = false)
	private String averageHeartRateBpm;

	@Column(name = ParameterName.TableNames.AVERAGE_POWER_WATTS, nullable = false)
	private String averagePowerWatts;

	@Column(name = ParameterName.TableNames.AVERAGE_WATTS_PER_KG, nullable = false)
	private String averageWattsPerKg;

	@Column(name = ParameterName.TableNames.BODY_MASS_KG, nullable = false)
	private String bodyMassKg;

	@Column(name = ParameterName.TableNames.CRITICAL_POWER_WATTS, nullable = false)
	private String criticalPowerWatts;

	@Column(name = ParameterName.TableNames.CADENCE_SPM, nullable = false)
	private String cadenceSpm;

	@Column(name = ParameterName.TableNames.VALUE, nullable = false)
	private String value;

	@Column(name = ParameterName.TableNames.DESCENT_METERS, nullable = false)
	private String descentMeters;

	@Column(name = ParameterName.TableNames.DISTANCE_KM, nullable = false)
	private String distanceKm;

	@Column(name = ParameterName.TableNames.DURATION_MINUTES, nullable = false)
	private String durationMinutes;

	@Column(name = ParameterName.TableNames.DURATION_SECONDS, nullable = false)
	private String durationSeconds;

	@Column(name = ParameterName.TableNames.FLIGHT_TIME_SECONDS, nullable = false)
	private String flightTimeSeconds;

	@Column(name = ParameterName.TableNames.FORM_POWER_WATTS, nullable = false)
	private String formPowerWatts;

	@Column(name = ParameterName.TableNames.FIRST_HALF_EFFICIENCY_FACTOR, nullable = false)
	private String firstHalfEfficiencyFactor;

	@Column(name = ParameterName.TableNames.GAP_SECONDS_PER_KM, nullable = false)
	private String gapSecondsPerKm;

	@Column(name = ParameterName.TableNames.GRADIENT, nullable = false)
	private String gradient;

	@Column(name = ParameterName.TableNames.GROUND_CONTACT_TIME_MILLIS, nullable = false)
	private String groundContactTimeMillis;

	@Column(name = ParameterName.TableNames.GROUND_CONTACT_TIME_SECONDS, nullable = false)
	private String groundContactTimeSeconds;

	@Column(name = ParameterName.TableNames.HEART_RATE_MAX_BPM, nullable = false)
	private String heartRateMaxBpm;

	@Column(name = ParameterName.TableNames.HEART_RATE_REST_BPM, nullable = false)
	private String heartRateRestBpm;

	@Column(name = ParameterName.TableNames.INTENSITY_FACTOR, nullable = false)
	private String intensityFactor;

	@Column(name = ParameterName.TableNames.LEG_COMPRESSION_METERS, nullable = false)
	private String legCompressionMeters;

	@Column(name = ParameterName.TableNames.MAX_HEART_RATE_BPM, nullable = false)
	private String maxHeartRateBpm;

	@Column(name = ParameterName.TableNames.NORMALIZED_POWER_WATTS, nullable = false)
	private String normalizedPowerWatts;

	@Column(name = ParameterName.TableNames.PACE_SECONDS_PER_KM, nullable = false)
	private String paceSecondsPerKm;

	@Column(name = ParameterName.TableNames.PEAK_FORCE_NEWTONS, nullable = false)
	private String peakForceNewtons;

	@Column(name = ParameterName.TableNames.POWER_WATTS, nullable = false)
	private String powerWatts;

	@Column(name = ParameterName.TableNames.RESTING_HEART_RATE_BPM, nullable = false)
	private String restingHeartRateBpm;

	@Column(name = ParameterName.TableNames.SECOND_HALF_EFFICIENCY_FACTOR, nullable = false)
	private String secondHalfEfficiencyFactor;

	@Column(name = ParameterName.TableNames.SPEED_METERS_PER_SECOND, nullable = false)
	private String speedMetersPerSecond;

	@Column(name = ParameterName.TableNames.STRIDE_LENGTH_METERS, nullable = false)
	private String strideLengthMeters;

	@Column(name = ParameterName.TableNames.STRIDE_TIME_SECONDS, nullable = false)
	private String strideTimeSeconds;

	@Column(name = ParameterName.TableNames.THRESHOLD_PACE_SECONDS_PER_KM, nullable = false)
	private String thresholdPaceSecondsPerKm;

	@Column(name = ParameterName.TableNames.TOTAL_RUNNING_POWER_WATTS, nullable = false)
	private String totalRunningPowerWatts;

	@Column(name = ParameterName.TableNames.VERTICAL_OSCILLATION_CM, nullable = false)
	private String verticalOscillationCm;
}
