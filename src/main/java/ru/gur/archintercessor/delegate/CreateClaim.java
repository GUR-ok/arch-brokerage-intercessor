package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateClaim extends AbstractCommonDelegate {

    @Override
    protected void doExecute(DelegateExecution delegateExecution) {
        System.out.println("!!! CreateClaim");
    }

    @Override
    protected String getErrorCode() {
        return "CreateClaimErrorCode";
    }
}
