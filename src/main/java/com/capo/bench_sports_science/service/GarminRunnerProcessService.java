package com.capo.bench_sports_science.service;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.capo.bench_sports_science.domain.shared.ChannelEvents;
import com.capo.bench_sports_science.event.GarminRawFileEvent;
import com.capo.bench_sports_science.repository.MinioRepository;

@Service
public class GarminRunnerProcessService {
	
	private final SseService sseService;
	private final MinioRepository minioStorageService;
	private final ApplicationEventPublisher eventPublisher;
	
	public GarminRunnerProcessService(SseService sseService, MinioRepository minioStorageService,
			ApplicationEventPublisher eventPublisher) {
		this.sseService= sseService;
		this.minioStorageService= minioStorageService;
		this.eventPublisher= eventPublisher;
	}
	
	public SseEmitter RunnerProcess(List<MultipartFile> fileParts, Jwt jwt) {
		SseEmitter emitter = sseService.subscribe(ChannelEvents.CHANNEL_FILE.value());
		var result="";
		try {
			String fileName = fileParts.get(0).getName();
			String userId = jwt.getClaimAsString("user_id");
			String fileIdentifier= minioStorageService.uploadCsvFile(fileParts.get(0), userId);
			eventPublisher.publishEvent(new GarminRawFileEvent(fileIdentifier, fileName, userId));
			result= "TODO UN EXITO";
		}catch(Exception e) {
			result= "ERROR";
		}
		sseService.sendEventToChannel(ChannelEvents.CHANNEL_FILE.value(),ChannelEvents.Events.EVENT_FILE, result);
		return emitter;
	}
}
