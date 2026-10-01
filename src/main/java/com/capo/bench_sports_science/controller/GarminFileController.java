package com.capo.bench_sports_science.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.capo.bench_sports_science.service.GarminRunnerProcessService;

@RestController
@RequestMapping("garmin-file")
public class GarminFileController {
	
	private final GarminRunnerProcessService garminRunnerProcessService;
	
	public GarminFileController(GarminRunnerProcessService garminRunnerProcessService) {
		this.garminRunnerProcessService = garminRunnerProcessService;
	}
	
	@PostMapping(path = "/raw-data", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@PreAuthorize("hasAnyRole('ATHLETE', 'COACH')") 
    public SseEmitter streamDataSummaryGarmin(@RequestPart("files") List<MultipartFile> fileParts, @AuthenticationPrincipal Jwt jwt) {
		return garminRunnerProcessService.RunnerProcess(fileParts, jwt);
	}
}
