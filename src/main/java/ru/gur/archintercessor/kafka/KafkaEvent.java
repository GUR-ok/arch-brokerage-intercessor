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
        @JsonSubTypes.Type(value = ProfileUpdatedEventData.class, name = "PROFILE_UPDATED")
})
public interface KafkaEvent extends EventSource {
}