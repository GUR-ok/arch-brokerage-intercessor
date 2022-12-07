package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.documentgenerator.DocumentGeneratorClient;
import ru.gur.archintercessor.interaction.documentgenerator.request.PrintedFormGenerationRequest;
import ru.gur.archintercessor.process.VariableKey;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class GenerateReport extends AbstractCommonDelegate {

    private final DocumentGeneratorClient documentGeneratorClient;

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {

        final GetProfileData.ProfileData data =
                (GetProfileData.ProfileData) delegateExecution.getVariable(VariableKey.PROFILE_DATA.name());

        final String agreementNumber = String.valueOf(delegateExecution.getVariable(VariableKey.AGREEMENT_NUMBER.name()));
        final String firstName = data.getName();
        final String passportNumber = data.getPassportNumber();
        final String product = String.valueOf(delegateExecution.getVariable(VariableKey.PRODUCT_ID.name()));

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("«dd» MMMM yyyy", new Locale("ru"));
        final String date = LocalDate.now().format(formatter);

        final PrintedFormGenerationRequest request = PrintedFormGenerationRequest.builder()
                .agreementNumber(agreementNumber)
                .passportNumber(passportNumber)
                .product(product)
                .firstName(firstName)
                .date(date)
                .build();

        final String reportFileName = documentGeneratorClient.createDocument(request);
        delegateExecution.setVariable(VariableKey.REPORT_FILE_NAME.name(), reportFileName);
    }
}
