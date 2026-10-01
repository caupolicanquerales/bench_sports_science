package com.capo.bench_sports_science.event;

public record GarminRawFileEvent(String fileIdentifier, String fileName, String userId) {

    public GarminRawFileEvent(String fileIdentifier, String fileName) {
        this(fileIdentifier, fileName, null);
    }
}
