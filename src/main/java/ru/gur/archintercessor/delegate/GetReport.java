package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.documentgenerator.DocumentGeneratorClient;
import ru.gur.archintercessor.process.VariableKey;

@Component
@RequiredArgsConstructor
public class GetReport extends AbstractCommonDelegate {

    private final DocumentGeneratorClient documentGeneratorClient;

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        final String reportFileName = (String) delegateExecution.getVariable(VariableKey.REPORT_FILE_NAME.name());

        final String url = documentGeneratorClient.getUrl(reportFileName);
        delegateExecution.setVariable(VariableKey.REPORT_URL.name(), url);
    }
}
