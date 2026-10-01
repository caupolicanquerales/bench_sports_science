package com.capo.bench_sports_science.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class SseService {

    private final Map<String, CopyOnWriteArrayList<SseEmitter>> channelEmitters = new ConcurrentHashMap<>();

    public SseEmitter subscribe(String channel) {
        SseEmitter emitter = new SseEmitter(30L * 60L * 1000L);

        CopyOnWriteArrayList<SseEmitter> emitters = channelEmitters.computeIfAbsent(
            channel,
            key -> new CopyOnWriteArrayList<>()
        );
        emitters.add(emitter);

        emitter.onCompletion(() -> removeEmitter(channel, emitter));
        emitter.onTimeout(() -> {
            emitter.complete();
            removeEmitter(channel, emitter);
        });
        emitter.onError(ex -> removeEmitter(channel, emitter));

        return emitter;
    }

    public void sendEventToChannel(String channel, String eventName, Object data) {
        CopyOnWriteArrayList<SseEmitter> emitters = channelEmitters.get(channel);
        if (emitters == null || emitters.isEmpty()) {
            return;
        }

        List<SseEmitter> deadEmitters = new CopyOnWriteArrayList<>();

        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name(eventName)
                        .data(data));
            } catch (IOException | IllegalStateException e) {
                deadEmitters.add(emitter);
            }
        }

        emitters.removeAll(deadEmitters);
        if (emitters.isEmpty()) {
            channelEmitters.remove(channel, emitters);
        }
    }

    private void removeEmitter(String channel, SseEmitter emitter) {
        CopyOnWriteArrayList<SseEmitter> emitters = channelEmitters.get(channel);
        if (emitters != null) {
            emitters.remove(emitter);
            if (emitters.isEmpty()) {
                channelEmitters.remove(channel, emitters);
            }
        }
    }
}
