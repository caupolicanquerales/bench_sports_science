SELECT 
    AVG(heart_rate_bpm) as avgHeartRate,
    MAX(heart_rate_bpm) as maxHeartRate,
    AVG(power_w) as avgPower,
    MAX(power_w) as maxPower,
    AVG(cadence_rpm) as avgCadence,
    AVG(speed_ms) as avgSpeedMs
FROM raw_garmin_registers
WHERE file_register_id = :fileRegisterId;