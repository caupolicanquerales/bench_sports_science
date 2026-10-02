-- 1. Add a spatial point geometry column if it doesn't exist
ALTER TABLE raw_garmin_registers 
ADD COLUMN IF NOT EXISTS geom geometry(Point, 4326);

-- 2. Populate spatial points from existing latitude/longitude
UPDATE raw_garmin_registers
SET geom = ST_SetSRID(ST_MakePoint(longitud, latitud), 4326)
WHERE latitud IS NOT NULL 
  AND longitud IS NOT NULL 
  AND geom IS NULL;

-- 3. Create an index for fast spatial joins
CREATE INDEX IF NOT EXISTS idx_raw_garmin_registers_geom 
ON raw_garmin_registers USING gist(geom);

-- 4. Create or replace the view to include elevation and geometry.
--    If the SRTM raster table has not been loaded yet, elevation_m stays NULL.
CREATE OR REPLACE VIEW garmin_leaflet_hotline_points AS
SELECT 
    r.file_register_id,
    r.time_stamp,
    r.latitud AS latitude,
    r.longitud AS longitude,
    ROUND((r.speed_ms * 3.6)::numeric, 1) AS speed_kmh,
    CASE
        WHEN to_regclass('public.srtm_elevation') IS NOT NULL THEN
            ST_Value(e.rast, r.geom)
        ELSE
            NULL
    END AS elevation_m
FROM raw_garmin_registers r
LEFT JOIN LATERAL (
    SELECT e.rast
    FROM public.srtm_elevation e
    WHERE ST_Intersects(e.rast, r.geom)
    ORDER BY e.rid
    LIMIT 1
) e ON TRUE
WHERE r.latitud IS NOT NULL 
  AND r.longitud IS NOT NULL
ORDER BY r.time_stamp ASC;