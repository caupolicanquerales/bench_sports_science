package com.capo.bench_sports_science.components;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.capo.bench_sports_science.dto.RawGarminCsvDto;
import com.capo.bench_sports_science.models.FileRegisterModel;
import com.capo.bench_sports_science.models.RawGarminRegisterModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
@StepScope
public class RawGarminItemProcessor implements ItemProcessor<RawGarminCsvDto, RawGarminRegisterModel> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Value("#{stepExecution.jobExecution.executionContext.get('fileRegisterId')}")
    private Long fileRegisterId;

	@Override
	public @Nullable RawGarminRegisterModel process(RawGarminCsvDto item) throws Exception {
		
		FileRegisterModel fileRegister = entityManager.getReference(FileRegisterModel.class, fileRegisterId);
		
		RawGarminRegisterModel entity = new RawGarminRegisterModel();
        entity.setFileRegister(fileRegister);
        if (item.timeStamp() != null && !item.timeStamp().isBlank()) {
            entity.setTimeStamp(OffsetDateTime.parse(item.timeStamp(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        entity.setLatitud(parseDouble(item.latitud()));
        entity.setLongitud(parseDouble(item.longitud()));
        entity.setDistanceM(parseDouble(item.distanceM()));
        entity.setSpeedMs(parseDouble(item.speedMs()));
        entity.setHeartRateBpm(parseInteger(item.heartRateBpm()));
        entity.setCadenceRpm(parseInteger(item.cadenceRpm()));
        entity.setPowerW(parseInteger(item.powerW()));

        return entity;
	}
	
	private Double parseDouble(String value) {
        if (value == null || value.isBlank() || "null".equalsIgnoreCase(value)) {
            return null;
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer parseInteger(String value) {
        if (value == null || value.isBlank() || "null".equalsIgnoreCase(value)) {
            return null;
        }
        try {
            return (int) Math.round(Double.parseDouble(value.trim()));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
