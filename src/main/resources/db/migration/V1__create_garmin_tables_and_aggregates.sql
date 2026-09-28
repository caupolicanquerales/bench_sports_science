-- 1. Raw trackpoint table definition
CREATE TABLE IF NOT EXISTS raw_garmin_registers (
    id BIGSERIAL NOT NULL,
    file_register_id BIGINT NOT NULL,
    time_stamp TIMESTAMP WITH TIME ZONE NOT NULL,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    distance_m DOUBLE PRECISION,
    speed_ms DOUBLE PRECISION,
    heart_rate_bpm INTEGER,
    cadence_rpm INTEGER,
    power_w INTEGER,
    PRIMARY KEY (id, time_stamp)
);

-- 2. Convert raw table into a TimescaleDB Hypertable partitioned by time_stamp
SELECT create_hypertable('raw_garmin_registers', 'time_stamp', if_not_exists => TRUE);

-- 3. Create Continuous Aggregate View for fast 1-minute chart buckets
CREATE MATERIALIZED VIEW garmin_chart_1min_buckets
WITH (timescale.continuous) AS
SELECT 
    file_register_id,
    time_bucket('1 minute', time_stamp) AS bucket,
    AVG(heart_rate_bpm)::INTEGER AS avg_hr,
    AVG(power_w)::INTEGER AS avg_power,
    AVG(cadence_rpm)::INTEGER AS avg_cadence,
    AVG(speed_ms) AS avg_speed
FROM raw_garmin_registers
GROUP BY file_register_id, bucket;

-- 4. Set up auto-refresh background policy for TimescaleDB
SELECT add_continuous_aggregate_policy('garmin_chart_1min_buckets',
    start_offset => INTERVAL '1 day',
    end_offset   => INTERVAL '1 second',
    schedule_interval => INTERVAL '1 minute');