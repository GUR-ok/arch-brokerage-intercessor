package ru.gur.archintercessor.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.gur.archintercessor.process.MessageCorrelation;
import ru.gur.archintercessor.process.VariableKey;
import ru.gur.archintercessor.web.request.Event;
import ru.gur.archintercessor.web.request.EventSource;
import ru.gur.archintercessor.web.request.ProductUpdatedEventData;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductUpdateHandler implements EventHandler<ProductUpdatedEventData> {

    private final RuntimeService runtimeService;

    @Override
    public boolean canHandle(final EventSource eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        return Event.PRODUCT_UPDATED.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(final ProductUpdatedEventData eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        runtimeService.createMessageCorrelation(MessageCorrelation.PRODUCT_UPDATED.getMessageRef())
                .processInstanceId(eventSource.getProcessId())
                .setVariable(VariableKey.PRODUCT_ID.name(), eventSource.getProductId())
                .correlate();

        log.info("Event handled: {}", eventSource);

        return eventSource.getProcessId();
    }
}
