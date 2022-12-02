package ru.gur.archintercessor.service.handler;

import ru.gur.archintercessor.web.request.EventSource;

public interface EventHandler <T extends EventSource> {

    boolean canHandle(EventSource eventSource);

    String handleEvent(T eventSource);
}
