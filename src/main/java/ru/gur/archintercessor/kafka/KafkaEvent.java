package ru.gur.archintercessor.kafka;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.gur.archintercessor.web.request.EventSource;
import ru.gur.archintercessor.web.request.ProfileUpdatedEventData;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "event"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProfileUpdatedEventData.class, name = "PROFILE_UPDATED"),
        @JsonSubTypes.Type(value = BrokerageNotificationEventData.class, name = "BROKERAGE_NOTIFICATION"),
        @JsonSubTypes.Type(value = ClaimInProgressEventData.class, name = "CLAIM_IN_PROGRESS"),
        @JsonSubTypes.Type(value = ClaimWaitForSignEventData.class, name = "CLAIM_WAIT_FOR_SIGN"),
        @JsonSubTypes.Type(value = ClaimCancelEventData.class, name = "CLAIM_CANCEL"),
        @JsonSubTypes.Type(value = ClaimDoneEventData.class, name = "CLAIM_DONE"),
        @JsonSubTypes.Type(value = ClaimRejectedEventData.class, name = "CLAIM_REJECTED"),
        @JsonSubTypes.Type(value = ClaimUpdateEventData.class, name = "UPDATE_CLAIM")
})
public interface KafkaEvent extends EventSource {
}