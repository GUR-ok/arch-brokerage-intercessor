package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.brokerageaccount.BrokerageAccountClient;
import ru.gur.archintercessor.interaction.brokerageaccount.request.CreateBrokerageAccountRq;
import ru.gur.archintercessor.process.VariableKey;

@Component
@RequiredArgsConstructor
public class CreateBrokerageAccount extends AbstractCommonDelegate {

    private final BrokerageAccountClient brokerageAccountClient;

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
            final CreateBrokerageAccountRq request = CreateBrokerageAccountRq.builder()
                    .agreementNumber((Long) delegateExecution.getVariable(VariableKey.AGREEMENT_NUMBER.name()))
                    .build();
    }
}
