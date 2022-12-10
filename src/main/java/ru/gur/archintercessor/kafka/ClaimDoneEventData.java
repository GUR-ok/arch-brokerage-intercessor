package ru.gur.archintercessor.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import ru.gur.archintercessor.interaction.claim.request.UpdateClaimRequest;
import ru.gur.archintercessor.web.request.Event;

import java.util.UUID;

@Data
@Builder
public class ClaimDoneEventData implements KafkaEvent {

    private UUID id;

    private UpdateClaimRequest updateClaimRequest;

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Prevents duplication when serializing to JSON (subtype discriminator property)
    public Event getEvent() {
        return Event.CLAIM_DONE;
    }
}
