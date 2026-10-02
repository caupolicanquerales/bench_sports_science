SELECT
    AVG(heart_rate_bpm)::INTEGER AS avgHeartRate,
    MAX(heart_rate_bpm)::INTEGER AS maxHeartRate,
    AVG(power_w)::INTEGER AS avgPower,
    MAX(power_w)::INTEGER AS maxPower,
    AVG(cadence_rpm)::INTEGER AS avgCadence,
    ROUND((AVG(speed_ms) * 3.6)::numeric, 1) AS avgSpeedKmh,
    EXTRACT(EPOCH FROM (MAX(time_stamp) - MIN(time_stamp)))::BIGINT AS totalTimeSeconds,
    CASE
        WHEN AVG(speed_ms) > 0 THEN ROUND((60.0 / (AVG(speed_ms) * 3.6))::numeric, 2)
        ELSE NULL
    END AS avgPaceMinPerKm,
    MAX(distance_m) AS totalDistanceM
FROM raw_garmin_registers
WHERE file_register_id = :fileRegisterId;