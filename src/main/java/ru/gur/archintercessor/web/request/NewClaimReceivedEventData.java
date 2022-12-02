package ru.gur.archintercessor.web.request;

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
public class NewClaimReceivedEventData implements HttpEvent {

    @NotBlank
    private String productId;

    @Override
    public Event getEvent() {
        return Event.NEW_CLAIM_RECEIVED;
    }
}
