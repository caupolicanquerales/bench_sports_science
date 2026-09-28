package com.capo.bench_sports_science.models;

import java.time.OffsetDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.capo.bench_sports_science.domain.shared.RawParameterGarminName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="RAW_GARMIN_REGISTERS")
@Getter
@Setter
@NoArgsConstructor
public class RawGarminRegisterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_REGISTER_ID", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private FileRegisterModel fileRegister;

	@Column(name = RawParameterGarminName.TableNames.TIME_STAMP, columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime timeStamp;

	@Column(name = RawParameterGarminName.TableNames.LATITUD, nullable = false)
	private Double latitud;

	@Column(name = RawParameterGarminName.TableNames.LONGITUD, nullable = false)
	private Double longitud;

	@Column(name = RawParameterGarminName.TableNames.DISTANCE_M, nullable = false)
	private Double distanceM;

	@Column(name = RawParameterGarminName.TableNames.SPEED_MS, nullable = false)
	private Double speedMs;

	@Column(name = RawParameterGarminName.TableNames.HEART_RATE_BPM)
	private Integer heartRateBpm;

	@Column(name = RawParameterGarminName.TableNames.CADENCE_RPM, nullable = false)
	private Integer cadenceRpm;

	@Column(name = RawParameterGarminName.TableNames.POWER_W)
	private Integer powerW;
}
