package ru.gur.archintercessor.service;

import ru.gur.archintercessor.web.request.EventSource;

public interface ProcessService {

    String processEvent(EventSource eventSource);
}
