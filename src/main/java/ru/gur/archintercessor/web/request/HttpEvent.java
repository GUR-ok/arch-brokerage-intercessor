package ru.gur.archintercessor.web.request;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "event"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = NewClaimReceivedEventData.class, name = "NEW_CLAIM_RECEIVED"),
        @JsonSubTypes.Type(value = ProfileUpdatedEventData.class, name = "PROFILE_UPDATED"),
        @JsonSubTypes.Type(value = ProductUpdatedEventData.class, name = "PRODUCT_UPDATED"),
        @JsonSubTypes.Type(value = AgreementSignedEventData.class, name = "AGREEMENT_SIGNED"),
        @JsonSubTypes.Type(value = AgreementSignCancelEventData.class, name = "CANCEL_SIGN"),
})
public interface HttpEvent extends EventSource{
}
