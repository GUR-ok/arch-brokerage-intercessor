package ru.gur.archintercessor.web.request;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "event"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = NewClaimReceivedEventData.class, name = "NEW_CLAIM_RECEIVED"),
        @JsonSubTypes.Type(value = ProfileUpdatedEventData.class, name = "PROFILE_UPDATED")
})
public interface HttpEvent extends EventSource{
}
