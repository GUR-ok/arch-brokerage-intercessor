package ru.gur.archintercessor.interaction.documentgenerator;

import ru.gur.archintercessor.interaction.documentgenerator.request.PrintedFormGenerationRequest;

public interface DocumentGeneratorClient {

    String createDocument(PrintedFormGenerationRequest printedFormGenerationRequest);
}
