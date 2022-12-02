package ru.gur.archintercessor.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.gur.archintercessor.service.ProcessService;
import ru.gur.archintercessor.web.request.HttpEvent;
import ru.gur.archintercessor.web.response.EventProcessingResult;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProcessEventControllerImpl implements ProcessEventController {

    private final ProcessService processService;

    @Override
    public EventProcessingResult processEvent(@Valid HttpEvent httpEvent) {
        return EventProcessingResult.builder()
                .processId(processService.processEvent(httpEvent))
                .build();
    }
}
