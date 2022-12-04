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
        @JsonSubTypes.Type(value = BrokerageNotificationEventData.class, name = "BROKERAGE_NOTIFICATION")
})
public interface KafkaEvent extends EventSource {
}