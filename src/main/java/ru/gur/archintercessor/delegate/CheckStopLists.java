package ru.gur.archintercessor.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.variables.VariableKey;

@Component
public class CheckStopLists extends AbstractCommonDelegate {

    @Override
    protected void doExecute(DelegateExecution delegateExecution) {
        delegateExecution.setVariable(VariableKey.STOPLIST_FAIL.name(), false);
    }
}
