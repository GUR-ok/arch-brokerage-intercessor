package ru.gur.archintercessor.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.web.client.RestClientException;

@Slf4j
public abstract class AbstractRetryableDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        try {
            System.out.println("---");
            System.out.println("ServiceTask: " + this.getClass().getSimpleName());
            System.out.println("ActivityId: " + execution.getCurrentActivityId());

            doExecute(execution);
        } catch (RestClientException restClientException) {
            log.error("Delegate {}; Exception: {}", this.getClass().getSimpleName(), restClientException);
            throw new RuntimeException("retry");
        } catch (Exception e) {
            log.error("Delegate {}; Exception: {}", this.getClass().getSimpleName(), e);
            throw new BpmnError(getErrorCode());
        }
    }

    protected abstract void doExecute(DelegateExecution delegateExecution);

    protected String getErrorCode() {
        return "commonErrorCode";
    }
}