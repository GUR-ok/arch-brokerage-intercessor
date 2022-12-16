package ru.gur.archintercessor.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@Validated
@Schema(description = "Событие \"Получена новая заявка\"")
public class NewClaimReceivedEventData implements HttpEvent {

    @NotBlank
    @Schema(description = "Идентификатор тарифа", example = "123")
    private String productId;

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // Prevents duplication when serializing to JSON (subtype discriminator property)
    public Event getEvent() {
        return Event.NEW_CLAIM_RECEIVED;
    }
}
