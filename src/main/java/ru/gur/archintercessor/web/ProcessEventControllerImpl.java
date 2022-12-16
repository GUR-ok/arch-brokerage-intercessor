package ru.gur.archintercessor.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.gur.archintercessor.service.ProcessService;
import ru.gur.archintercessor.web.filter.RequestScopedDataProvider;
import ru.gur.archintercessor.web.filter.SessionScopedDataProvider;
import ru.gur.archintercessor.web.request.HttpEvent;
import ru.gur.archintercessor.web.response.EventProcessingResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Enumeration;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProcessEventControllerImpl implements ProcessEventController {

    private final ProcessService processService;
    private final RequestScopedDataProvider requestScopedDataProvider;
    private final SessionScopedDataProvider sessionScopedDataProvider;

    @Override
    public EventProcessingResult processEvent(@Valid HttpEvent httpEvent, HttpServletRequest httpServletRequest) {
        System.out.println("!!! HEADERS");
        Enumeration headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = httpServletRequest.getHeader(key);
            log.info(key + " " + value);
        }

        requestScopedDataProvider.setProfileId(
                Optional.ofNullable(httpServletRequest.getHeader("x-custom"))
                        .or(() -> Optional.ofNullable(httpServletRequest.getHeader("profileId")))
                        .map(UUID::fromString)
                        .orElseThrow(() -> new RuntimeException("ProfilId is null")));
sessionScopedDataProvider.setProfileId(
        Optional.ofNullable(httpServletRequest.getHeader("x-custom"))
                .or(() -> Optional.ofNullable(httpServletRequest.getHeader("profileId")))
                .map(UUID::fromString)
                .orElseThrow(() -> new RuntimeException("ProfilId is null")));

        return EventProcessingResult.builder()
                .processId(processService.processEvent(httpEvent))
                .build();
    }
}
