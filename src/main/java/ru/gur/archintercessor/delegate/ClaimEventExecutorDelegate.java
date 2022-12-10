package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.claim.ClaimClient;
import ru.gur.archintercessor.interaction.claim.event.ClaimEventType;
import ru.gur.archintercessor.interaction.claim.request.ClaimData;
import ru.gur.archintercessor.process.VariableKey;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClaimEventExecutorDelegate extends AbstractCommonDelegate {

    private final ClaimClient claimClient;

    private static final String LOCAL_VAR_REF = "ClaimEventType";

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        final ClaimEventType claimEventType = ClaimEventType.valueOf(
                (String) delegateExecution.getVariableLocal(LOCAL_VAR_REF)
        );

        final GetProfileData.ProfileData profileData
                = (GetProfileData.ProfileData) delegateExecution.getVariable(VariableKey.PROFILE_DATA.name());

        final ClaimData claimData = ClaimData.builder()
                .id((UUID) delegateExecution.getVariable(VariableKey.CLAIM_ID.name()))
                .agreementNumber((Long) delegateExecution.getVariable(VariableKey.AGREEMENT_NUMBER.name()))
                .brokerageAccountId((UUID) delegateExecution.getVariable(VariableKey.ACCOUNT_ID.name()))
                .firstName(profileData.getName())
                .build();

        claimClient.executeEvent(claimEventType, claimData, delegateExecution.getProcessInstanceId());
    }
}
