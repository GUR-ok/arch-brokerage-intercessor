package ru.gur.archintercessor.interaction.documentgenerator.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class PrintedFormGenerationRequest {

    @NotBlank
    private String agreementNumber;

    @NotBlank
    private String firstName;

    @NotBlank
    private String passportNumber;

    @NotBlank
    private String product;

    @NotBlank
    private String date;
}
