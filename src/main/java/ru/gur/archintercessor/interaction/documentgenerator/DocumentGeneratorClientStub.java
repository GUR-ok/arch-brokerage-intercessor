package ru.gur.archintercessor.interaction.documentgenerator;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.documentgenerator.request.PrintedFormGenerationRequest;

@Primary
@Component
@ConditionalOnProperty(prefix = "interaction", name = "documentgenerator.stubEnabled", matchIfMissing = false)
public class DocumentGeneratorClientStub implements DocumentGeneratorClient {

    @Override
    public String createDocument(final PrintedFormGenerationRequest printedFormGenerationRequest) {
        System.out.println("STUB method " + Thread.currentThread().getStackTrace()[0].getMethodName() + " of class" +
                this.getClass().getSimpleName() + " called");

        return RandomStringUtils.randomAlphanumeric(25);
    }

    @Override
    public String getUrl(String reportName) {
        return "some_url";
    }
}
