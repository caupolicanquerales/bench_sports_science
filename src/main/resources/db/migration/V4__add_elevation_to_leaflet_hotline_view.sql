CREATE OR REPLACE VIEW garmin_leaflet_hotline_points AS
SELECT
    r.file_register_id,
    r.time_stamp,
    r.latitud AS latitude,
    r.longitud AS longitude,
    ROUND((r.speed_ms * 3.6)::numeric, 1) AS speed_kmh,
    CASE
        WHEN to_regclass('public.srtm_elevation') IS NOT NULL THEN
            ST_Value(e.rast, COALESCE(r.geom, ST_SetSRID(ST_MakePoint(r.longitud, r.latitud), 4326)))
        ELSE
            NULL
    END AS elevation_m
FROM raw_garmin_registers r
LEFT JOIN LATERAL (
    SELECT e.rast
    FROM public.srtm_elevation e
    WHERE ST_Intersects(e.rast, COALESCE(r.geom, ST_SetSRID(ST_MakePoint(r.longitud, r.latitud), 4326)))
    ORDER BY e.rid
    LIMIT 1
) e ON TRUE
WHERE r.latitud IS NOT NULL
  AND r.longitud IS NOT NULL
ORDER BY r.time_stamp ASC;
