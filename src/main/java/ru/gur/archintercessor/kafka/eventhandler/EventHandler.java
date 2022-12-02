package ru.gur.archintercessor.kafka.eventhandler;

import ru.gur.archintercessor.kafka.EventSource;

public interface EventHandler<T extends EventSource> {

    boolean canHandle(EventSource eventSource);

    String handleEvent(T eventSource);
}
