package ru.gur.archintercessor.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.gur.archintercessor.process.MessageCorrelation;
import ru.gur.archintercessor.process.VariableKey;
import ru.gur.archintercessor.web.request.AgreementSignCancelEventData;
import ru.gur.archintercessor.web.request.Event;
import ru.gur.archintercessor.web.request.EventSource;

@Slf4j
@Component
@RequiredArgsConstructor
public class AgreementSignCanceledHandler implements EventHandler<AgreementSignCancelEventData> {

    private final RuntimeService runtimeService;

    @Override
    public boolean canHandle(final EventSource eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        return Event.CANCEL_SIGN.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(AgreementSignCancelEventData eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        runtimeService.createMessageCorrelation(MessageCorrelation.AGREEMENT_SIGNED.getMessageRef())
                .processInstanceId(eventSource.getProcessId())
                .setVariable(VariableKey.CANCEL_FLAG.name(), true)
                .correlate();

        log.info("Event handled: {}", eventSource);

        return eventSource.getProcessId();
    }
}
