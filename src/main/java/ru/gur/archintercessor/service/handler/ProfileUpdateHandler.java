package ru.gur.archintercessor.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.gur.archintercessor.variables.VariableKey;
import ru.gur.archintercessor.web.request.Event;
import ru.gur.archintercessor.web.request.EventSource;
import ru.gur.archintercessor.web.request.ProfileUpdatedEventData;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProfileUpdateHandler implements EventHandler<ProfileUpdatedEventData> {

    private final RuntimeService runtimeService;

    @Override
    public boolean canHandle(final EventSource eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        return Event.PROFILE_UPDATED.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(final ProfileUpdatedEventData eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        runtimeService.createMessageCorrelation("ProfileUpdated")
                .processInstanceId(eventSource.getProcessId())
                .setVariable(VariableKey.PROFILE_DATA.name(), eventSource.getProfileData())
                .correlate();

        log.info("Event handled: {}", eventSource);

        return eventSource.getProcessId();
    }
}
