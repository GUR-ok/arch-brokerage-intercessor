package ru.gur.archintercessor.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReturnDelegate implements JavaDelegate {

    @Override
    public void execute(final DelegateExecution delegateExecution) {
        try {
            delegateExecution.getProcessEngineServices().getRuntimeService()
                    .createProcessInstanceModification(delegateExecution.getProcessInstanceId())
                    .startAfterActivity("Activity_createClaim")
                    .execute();
        } catch (Exception e) {
            log.error("Delegate {}; Exception: {}", this.getClass().getSimpleName(), e);
            throw new BpmnError("error");
        }
    }
}
