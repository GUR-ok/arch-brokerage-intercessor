package ru.gur.archintercessor.delegate;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.kafka.BrokerageNotificationEventData;
import ru.gur.archintercessor.kafka.Producer;
import ru.gur.archintercessor.process.VariableKey;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationDelegate extends AbstractCommonDelegate {

    private final Producer producer;

    private static final String LOCAL_VAR_REF = "NotificationType";
    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        final Boolean flag = (Boolean) delegateExecution.getVariable((String) delegateExecution.getVariableLocal(LOCAL_VAR_REF));
        if (flag == null || !flag) {
            System.out.println("!!! CLIENT NOTIFIED " + delegateExecution.getVariableLocal(LOCAL_VAR_REF));
            final BrokerageNotificationEventData brokerageNotificationEventData =
                    BrokerageNotificationEventData.builder()
                            .accountId((UUID) delegateExecution.getVariable(VariableKey.PROFILE_ID.name()))
                            .message((String) delegateExecution.getVariableLocal(LOCAL_VAR_REF))
                            .build();

            try {
                producer.sendEvent(Producer.NOTIFICATION_TOPIC, delegateExecution.getProcessInstanceId(),
                        brokerageNotificationEventData);
                delegateExecution.setVariable((String) delegateExecution.getVariableLocal(LOCAL_VAR_REF), true);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }
        }
    }
}
