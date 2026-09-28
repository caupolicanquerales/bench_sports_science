package com.capo.bench_sports_science.repository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.capo.bench_sports_science.dto.GarminChartPointDto;
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
                ((java.sql.Timestamp) row[0]).toInstant().atOffset(java.time.ZoneOffset.UTC),
                getIntegerValue(row[1]),
                getIntegerValue(row[2]),
                getIntegerValue(row[3]),
                getDoubleValue(row[4])
        )).toList();
    }
	
	private Double getDoubleValue(Object object) {
		return object != null ? ((Number) object).doubleValue() : null;
	}
	
	private Integer getIntegerValue(Object object) {
		return object != null ? ((Number) object).intValue() : null;
	}
}
