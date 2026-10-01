package com.capo.bench_sports_science.repository;

import java.io.InputStream;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

@Repository
public class MinioRepository {
	
	private final MinioClient minioClient;
	
	@Value("${minio.bucket-name}")
    private String defaultBucket;

    public MinioRepository(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public String uploadCsvFile(MultipartFile file, String userId) throws Exception {
        
    	boolean found = minioClient.bucketExists(
            BucketExistsArgs.builder().bucket(defaultBucket).build()
        );
        if (!found) {
            minioClient.makeBucket(
                MakeBucketArgs.builder().bucket(defaultBucket).build()
            );
        }

        String objectName = "garmin-data-" + userId + "-" + file.getOriginalFilename() + "-" + Instant.now().toEpochMilli();

        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(defaultBucket)
                    .object(objectName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType() != null ? file.getContentType() : "text/csv")
                    .build()
            );
        }

        return objectName;
    }
    
    public InputStream getFileStream(String objectName) throws Exception {
        return minioClient.getObject(
            GetObjectArgs.builder()
                .bucket(defaultBucket)
                .object(objectName)
                .build()
        );
    }
}
