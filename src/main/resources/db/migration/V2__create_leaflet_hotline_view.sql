-- View to format Garmin GPS points directly for Leaflet Hotline charts
CREATE OR REPLACE VIEW garmin_leaflet_hotline_points AS
SELECT 
    file_register_id,
    time_stamp,
    latitud AS latitude,
    longitud AS longitude,
    ROUND((speed_ms * 3.6)::numeric, 1) AS speed_kmh
FROM raw_garmin_registers
WHERE latitud IS NOT NULL 
  AND longitud IS NOT NULL
ORDER BY time_stamp ASC;