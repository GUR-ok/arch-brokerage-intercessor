package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import ru.gur.archintercessor.exception.ClaimAlreadyExistException;
import ru.gur.archintercessor.interaction.claim.ClaimClient;
import ru.gur.archintercessor.interaction.claim.request.CreateClaimRequest;
import ru.gur.archintercessor.process.VariableKey;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateClaim extends AbstractRetryableDelegate {

    private final ClaimClient claimClient;

    @Override
    public void execute(DelegateExecution execution) {
        try {
            System.out.println("---");
            System.out.println("ServiceTask: " + this.getClass().getSimpleName());
            System.out.println("ActivityId: " + execution.getCurrentActivityId());

            doExecute(execution);
        } catch (RestClientException e) {
            log.error("Delegate {}; Exception: {}", this.getClass().getSimpleName(), e);
            throw new RuntimeException("retry");
        } catch (ClaimAlreadyExistException e) {
            log.error("Delegate {}; Exception: {}", this.getClass().getSimpleName(), e);
            throw new BpmnError("CreateClaimErrorCode");
        } catch (Exception e) {
            log.error("Delegate {}; Exception: {}", this.getClass().getSimpleName(), e);
            throw new BpmnError(getErrorCode());
        }
    }

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        final CreateClaimRequest createClaimRequest = CreateClaimRequest.builder()
                .processId(delegateExecution.getProcessInstanceId())
                .profileId((UUID) delegateExecution.getVariable(VariableKey.PROFILE_ID.name()))
                .build();
        final UUID claimId = claimClient.createNewClaim(createClaimRequest);

        delegateExecution.setVariable(VariableKey.CLAIM_ID.name(), claimId);
    }
}
