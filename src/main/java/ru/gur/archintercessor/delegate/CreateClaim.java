package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.claim.ClaimClient;
import ru.gur.archintercessor.interaction.claim.request.CreateClaimRequest;
import ru.gur.archintercessor.process.VariableKey;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateClaim extends AbstractCommonDelegate {

    private final ClaimClient claimClient;

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        final CreateClaimRequest createClaimRequest = CreateClaimRequest.builder()
                .processId(delegateExecution.getProcessInstanceId())
                .profileId((UUID) delegateExecution.getVariable(VariableKey.PROFILE_ID.name()))
                .build();
        final UUID claimId = claimClient.createNewClaim(createClaimRequest);

        delegateExecution.setVariable(VariableKey.CLAIM_ID.name(), claimId);
    }

    @Override
    protected String getErrorCode() {
        return "CreateClaimErrorCode";
    }
}
