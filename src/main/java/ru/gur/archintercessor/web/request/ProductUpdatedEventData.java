package ru.gur.archintercessor.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@Validated
public class ProductUpdatedEventData implements HttpEvent {

    @NotBlank
    private String processId;

    @NotBlank
    private String productId;

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // Prevents duplication when serializing to JSON (subtype discriminator property)
    public Event getEvent() {
        return Event.PRODUCT_UPDATED;
    }
}
