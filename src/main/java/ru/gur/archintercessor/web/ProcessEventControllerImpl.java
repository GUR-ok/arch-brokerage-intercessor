package ru.gur.archintercessor.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.gur.archintercessor.service.ProcessService;
import ru.gur.archintercessor.web.filter.RequestScopedDataProvider;
import ru.gur.archintercessor.web.request.HttpEvent;
import ru.gur.archintercessor.web.response.EventProcessingResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProcessEventControllerImpl implements ProcessEventController {

    private final ProcessService processService;
    private final RequestScopedDataProvider requestScopedDataProvider;

    @Override
    public EventProcessingResult processEvent(@Valid HttpEvent httpEvent, HttpServletRequest httpServletRequest) {
        requestScopedDataProvider.setProfileId(
                Optional.ofNullable(httpServletRequest.getHeader("profileId"))
                        .map(UUID::fromString)
                        .orElse(null)
        );

        return EventProcessingResult.builder()
                .processId(processService.processEvent(httpEvent))
                .build();
    }
}
