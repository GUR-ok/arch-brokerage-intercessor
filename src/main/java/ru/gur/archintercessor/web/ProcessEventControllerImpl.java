package ru.gur.archintercessor.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.gur.archintercessor.service.ProcessService;
import ru.gur.archintercessor.web.request.HttpEvent;
import ru.gur.archintercessor.web.response.EventProcessingResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProcessEventControllerImpl implements ProcessEventController {

    private final ProcessService processService;

    @Override
    public EventProcessingResult processEvent(@Valid HttpEvent httpEvent, HttpServletRequest httpServletRequest) {
        return EventProcessingResult.builder()
                .processId(processService.processEvent(httpEvent))
                .build();
    }
}
