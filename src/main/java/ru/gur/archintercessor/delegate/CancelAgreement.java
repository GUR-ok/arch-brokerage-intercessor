package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.agreement.AgreementClient;
import ru.gur.archintercessor.process.VariableKey;

@Component
@RequiredArgsConstructor
public class CancelAgreement extends AbstractCommonDelegate {

    private final AgreementClient agreementClient;

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        agreementClient.cancelAgreement((Long) delegateExecution.getVariable(VariableKey.AGREEMENT_NUMBER.name()));
    }
}