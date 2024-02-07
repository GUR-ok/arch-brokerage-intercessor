package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecondDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        var test = delegateExecution.getVariable("TestVar");
        var gur = delegateExecution.getVariable("gur");
        System.out.println("Hello from secondDelegate " + test + " " + gur);

        if (gur == "hhh") {
            throw new RuntimeException("Error");
        }
    }
}
