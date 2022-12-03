package ru.gur.archintercessor.service.handler;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.gur.archintercessor.process.VariableKey;
import ru.gur.archintercessor.web.filter.RequestScopedDataProvider;
import ru.gur.archintercessor.web.request.Event;
import ru.gur.archintercessor.web.request.EventSource;
import ru.gur.archintercessor.web.request.NewClaimReceivedEventData;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NewClaimReceivedHandler implements EventHandler<NewClaimReceivedEventData> {

    private static final String PROCESS_KEY = "BROKERAGE_PROCESS_KEY";

    private final RuntimeService runtimeService;
    private final RequestScopedDataProvider requestScopedDataProvider;

    @Override
    public boolean canHandle(final EventSource eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        return Event.NEW_CLAIM_RECEIVED.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(final NewClaimReceivedEventData eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        Map<String,Object> variables = new HashMap<>();
        variables.put(VariableKey.PRODUCT_ID.name(), eventSource.getProductId());
        variables.put(VariableKey.PROFILE_ID.name(), requestScopedDataProvider.getProfileId());

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_KEY, variables);

        return processInstance.getId();
    }
}
