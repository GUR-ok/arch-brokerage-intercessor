package ru.gur.archintercessor.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@Validated
@Schema(description = "Событие \"Тариф изменен\"")
public class ProductUpdatedEventData implements HttpEvent {

    @NotBlank
    @Schema(description = "Идентификатор процесса", example = "e1345c75-79e5-11ed-b6ba-0a0027000005")
    private String processId;

    @NotBlank
    @Schema(description = "Идентификатор тарифа", example = "111")
    private String productId;

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // Prevents duplication when serializing to JSON (subtype discriminator property)
    public Event getEvent() {
        return Event.PRODUCT_UPDATED;
    }
}
