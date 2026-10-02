CREATE EXTENSION IF NOT EXISTS postgis;
CREATE EXTENSION IF NOT EXISTS postgis_raster;

CREATE TABLE IF NOT EXISTS public.srtm_elevation (
    rid SERIAL PRIMARY KEY,
    rast raster
);

CREATE INDEX IF NOT EXISTS idx_srtm_elevation_rast
ON public.srtm_elevation USING gist (ST_ConvexHull(rast));

COMMENT ON TABLE public.srtm_elevation IS 'SRTM elevation raster tiles loaded for terrain lookup. Leave empty until the raster is imported.';

ALTER TABLE raw_garmin_registers
ADD COLUMN IF NOT EXISTS geom geometry(Point, 4326);

UPDATE raw_garmin_registers
SET geom = ST_SetSRID(ST_MakePoint(longitud, latitud), 4326)
WHERE latitud IS NOT NULL
  AND longitud IS NOT NULL
  AND geom IS NULL;

CREATE INDEX IF NOT EXISTS idx_raw_garmin_registers_geom
ON raw_garmin_registers USING gist(geom);