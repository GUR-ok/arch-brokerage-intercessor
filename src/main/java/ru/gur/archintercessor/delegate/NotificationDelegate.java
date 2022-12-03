package ru.gur.archintercessor.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

@Component
public class NotificationDelegate extends AbstractCommonDelegate {

    private static final String LOCAL_VAR_REF = "NotificationType";
    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        System.out.println("!!! CLIENT NOTIFIED " + delegateExecution.getVariableLocal(LOCAL_VAR_REF));
    }
}
