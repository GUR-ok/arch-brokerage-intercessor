package ru.gur.archintercessor.web.request;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(
        description = "Событие",
        discriminatorProperty = "event",
        discriminatorMapping = {
                @DiscriminatorMapping(schema = NewClaimReceivedEventData.class, value = "NEW_CLAIM_RECEIVED"),
                @DiscriminatorMapping(schema = ProfileUpdatedEventData.class, value = "PROFILE_UPDATED"),
                @DiscriminatorMapping(schema = ProductUpdatedEventData.class, value = "PRODUCT_UPDATED"),
                @DiscriminatorMapping(schema = AgreementSignedEventData.class, value = "AGREEMENT_SIGNED"),
                @DiscriminatorMapping(schema = AgreementSignCancelEventData.class, value = "CANCEL_SIGN"),
        }
)
public interface HttpEvent extends EventSource{
}
