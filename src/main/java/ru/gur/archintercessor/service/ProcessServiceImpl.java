package ru.gur.archintercessor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.gur.archintercessor.service.handler.EventHandler;
import ru.gur.archintercessor.web.request.EventSource;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {

    private final Set<EventHandler<EventSource>> eventHandlers;

    @Override
    public String processEvent(final EventSource eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        return eventHandlers.stream()
                .filter(eventSourceEventHandler -> eventSourceEventHandler.canHandle(eventSource))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Handler for eventsource not found"))
                .handleEvent(eventSource);
    }
}
