package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.process.VariableKey;

@Component
@RequiredArgsConstructor
public class CodeSender extends AbstractCommonDelegate {

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        final String key = RandomStringUtils.randomNumeric(4);
        delegateExecution.setVariable(VariableKey.SIGN_KEY_EXPECTED.name(), key);

        System.out.println("!!! CLIENT NOTIFIED " + key);
    }
}
