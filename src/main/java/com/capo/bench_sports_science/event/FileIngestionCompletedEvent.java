package com.capo.bench_sports_science.event;

public record FileIngestionCompletedEvent(Long fileRegisterId, long timestamp) {
	
	public FileIngestionCompletedEvent(Long fileRegisterId) {
        this(fileRegisterId, System.currentTimeMillis());
    }
}
