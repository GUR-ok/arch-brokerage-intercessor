package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        delegateExecution.setVariable("gur", "hhh");

        System.out.println("Hello world");
    }
}
