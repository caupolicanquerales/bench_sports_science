package com.capo.bench_sports_science.repository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.capo.bench_sports_science.dto.GarminChartPointDto;
import com.capo.bench_sports_science.dto.GarminGPSPointDto;
import com.capo.bench_sports_science.dto.GarminSummaryDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class GarminAnalyticSummaryRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Value("classpath:sql/calculate_averages.sql")
    private Resource sqlResource;
	
	public GarminSummaryDto fetchActivitySummary(Long fileRegisterId) {
        try (InputStream inputStream = sqlResource.getInputStream()) {
            String sql = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            Object[] result = (Object[]) entityManager.createNativeQuery(sql)
                    .setParameter("fileRegisterId", fileRegisterId)
                    .getSingleResult();

            return new GarminSummaryDto(
                    getIntegerValue(result[0]),
                    getIntegerValue(result[1]),
                    getIntegerValue(result[2]),
                    getIntegerValue(result[3]),
                    getIntegerValue(result[4]),
                    getDoubleValue(result[5]));

        } catch (IOException e) {
            throw new IllegalStateException("Error loading summary SQL script", e);
        }
    }
	
	
	@SuppressWarnings("unchecked")
    public List<GarminChartPointDto> fetchChartPoints(Long fileRegisterId) {
        String sql = """
            SELECT bucket, avg_hr, avg_power, avg_cadence, (avg_speed * 3.6) as speed_kmh
            FROM garmin_chart_1min_buckets
            WHERE file_register_id = :fileRegisterId
            ORDER BY bucket ASC
        """;

        List<Object[]> rows = entityManager.createNativeQuery(sql)
                .setParameter("fileRegisterId", fileRegisterId)
                .getResultList();

        return rows.stream().map(row -> new GarminChartPointDto(
                toOffsetDateTime(row[0]),
                getIntegerValue(row[1]),
                getIntegerValue(row[2]),
                getIntegerValue(row[3]),
                getDoubleValue(row[4])
        )).toList();
    }
	
	@SuppressWarnings("unchecked")
    public List<GarminGPSPointDto> fetchGPSChartPoints(Long fileRegisterId) {
        String sql = """
            SELECT latitude, longitude, speed_kmh
            FROM garmin_leaflet_hotline_points
            WHERE file_register_id = :fileRegisterId
        """;

        List<Object[]> rows = entityManager.createNativeQuery(sql)
                .setParameter("fileRegisterId", fileRegisterId)
                .getResultList();

        return rows.stream().map(row -> new GarminGPSPointDto(
        		getDoubleValue(row[0]),
        		getDoubleValue(row[1]),
                getDoubleValue(row[2])
        )).toList();
    }
	
	private OffsetDateTime toOffsetDateTime(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof OffsetDateTime odt) {
			return odt;
		}
		if (value instanceof Instant instant) {
			return instant.atOffset(ZoneOffset.UTC);
		}
		if (value instanceof Timestamp ts) {
			return ts.toInstant().atOffset(ZoneOffset.UTC);
		}
		return OffsetDateTime.parse(value.toString());
	}
	
	private Double getDoubleValue(Object object) {
		return object != null ? ((Number) object).doubleValue() : null;
	}
	
	private Integer getIntegerValue(Object object) {
		return object != null ? ((Number) object).intValue() : null;
	}
}
