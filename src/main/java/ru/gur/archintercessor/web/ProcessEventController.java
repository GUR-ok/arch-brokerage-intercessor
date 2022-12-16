package ru.gur.archintercessor.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
    @Operation(summary = "Обработать событие")
    EventProcessingResult processEvent(@Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
            @ExampleObject(
                    name = "Person sample",
                    summary = "person example",
                    value =
                            "{\"event\":\"NEW_CLAIM_RECEIVED\",\"productId\":\"123\"}"
            )
    })) HttpEvent httpEvent, HttpServletRequest httpServletRequest);
}
