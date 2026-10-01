package com.capo.bench_sports_science.controller;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.capo.bench_sports_science.domain.shared.ChannelEvents;
import com.capo.bench_sports_science.service.SseService;

@RestController
@RequestMapping("garmin-data")
public class GarminDataController {
	
	private final SseService sseService;
	
	public GarminDataController(SseService sseService) {
		this.sseService= sseService;
	}
	
	@GetMapping(path = "/stream-data-summary", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@PreAuthorize("hasAnyRole('ATHLETE', 'COACH')")
    public SseEmitter streamDataSummaryGarmin(@AuthenticationPrincipal Jwt jwt) {
		return sseService.subscribe(ChannelEvents.CHANNEL_SUMMARY.value());
	}
	
	@GetMapping(path = "/stream-data-charts", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@PreAuthorize("hasAnyRole('ATHLETE', 'COACH')")
    public SseEmitter streamDataChartsGarmin(@AuthenticationPrincipal Jwt jwt) {
		return sseService.subscribe(ChannelEvents.CHANNEL_CHARTS.value());
	}
	
	@GetMapping(path = "/stream-data-gps", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@PreAuthorize("hasAnyRole('ATHLETE', 'COACH')")
    public SseEmitter streamDataGpsGarmin(@AuthenticationPrincipal Jwt jwt) {
		return sseService.subscribe(ChannelEvents.CHANNEL_GPS.value());
	}
}
