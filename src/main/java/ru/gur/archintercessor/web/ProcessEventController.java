package ru.gur.archintercessor.web;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gur.archintercessor.web.request.HttpEvent;
import ru.gur.archintercessor.web.response.EventProcessingResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static ru.gur.archintercessor.web.ProcessEventController.ROOT_PATH;

@Validated
@RequestMapping(ROOT_PATH)
public interface ProcessEventController {

    String ROOT_PATH = "/process/events";

    @PostMapping
    EventProcessingResult processEvent(@Valid @RequestBody HttpEvent httpEvent,
                                       HttpServletRequest httpServletRequest);
}
