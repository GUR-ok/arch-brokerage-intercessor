package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.process.VariableKey;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CodeCheck extends AbstractCommonDelegate {

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        final String keyExpected = (String) delegateExecution.getVariable(VariableKey.SIGN_KEY_EXPECTED.name());
        final String keyActual = (String) delegateExecution.getVariable(VariableKey.SIGN_KEY_ACTUAL.name());

        delegateExecution.setVariable(VariableKey.KEY_CHECK_RESULT.name(), Objects.equals(keyExpected, keyActual));
    }
}
