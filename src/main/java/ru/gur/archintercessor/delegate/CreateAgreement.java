package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.agreement.AgreementClient;
import ru.gur.archintercessor.interaction.agreement.request.AgreementCreationRequest;
import ru.gur.archintercessor.process.VariableKey;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateAgreement extends AbstractCommonDelegate {

    private final AgreementClient agreementClient;

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        final AgreementCreationRequest request = AgreementCreationRequest.builder()
                .claimId((UUID) delegateExecution.getVariable(VariableKey.CLAIM_ID.name()))
                .profileId((UUID) delegateExecution.getVariable(VariableKey.PROFILE_ID.name()))
                .productId((String) delegateExecution.getVariable(VariableKey.PRODUCT_ID.name()))
                .build();

        final Long agreementNumber = agreementClient.createAgreement(request);
        delegateExecution.setVariable(VariableKey.AGREEMENT_NUMBER.name(), agreementNumber);
    }
}