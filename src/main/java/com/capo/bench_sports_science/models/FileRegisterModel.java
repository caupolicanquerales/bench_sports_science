package com.capo.bench_sports_science.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="FILE_REGISTERS")
@Getter
@Setter
@NoArgsConstructor
public class FileRegisterModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "FILE_NAME", nullable = false)
	private String fileName;
	
	@Column(name = "USER_ID", nullable = false)
	private String userId;
	
	@CreationTimestamp
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 1:1 relationship with summary metrics
    @OneToOne(mappedBy = "fileRegister", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private GarminRegisterModel garminRegister;

    // 1:N relationship with raw time-series trackpoint records
    @OneToMany(mappedBy = "fileRegister", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RawGarminRegisterModel> rawGarminRegisters = new ArrayList<>();
}
