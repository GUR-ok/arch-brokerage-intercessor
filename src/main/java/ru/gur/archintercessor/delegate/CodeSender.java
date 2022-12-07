package ru.gur.archintercessor.delegate;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.kafka.BrokerageNotificationEventData;
import ru.gur.archintercessor.kafka.Producer;
import ru.gur.archintercessor.process.VariableKey;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class CodeSender extends AbstractCommonDelegate {

    private final Producer producer;

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        final String key = RandomStringUtils.randomNumeric(4);
        delegateExecution.setVariable(VariableKey.SIGN_KEY_EXPECTED.name(), key);

        System.out.println("!!! CLIENT NOTIFIED " + key);

        final BrokerageNotificationEventData brokerageNotificationEventData =
                BrokerageNotificationEventData.builder()
                        .accountId((UUID) delegateExecution.getVariable(VariableKey.PROFILE_ID.name()))
                        .message("Для подтверждения заявки введите код " + key + " Договор доступен по ссылке: " +
                                delegateExecution.getVariable(VariableKey.REPORT_URL.name()))
                        .build();

        try {
            producer.sendEvent(Producer.NOTIFICATION_TOPIC, delegateExecution.getProcessInstanceId(),
                    brokerageNotificationEventData);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
