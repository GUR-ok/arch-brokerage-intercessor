package ru.gur.archintercessor.process;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageCorrelation {

    AGREEMENT_SIGNED("AgreementSigned"),
    PRODUCT_UPDATED("ProductChanged"),
    PROFILE_UPDATE("ProfileUpdated");

    private final String messageRef;

    @JsonValue
    public String getValue() {
        return this.messageRef;
    }

    public String toString() {
        return String.valueOf(messageRef);
    }
}
