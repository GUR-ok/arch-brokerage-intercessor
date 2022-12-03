package ru.gur.archintercessor.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import ru.gur.archintercessor.delegate.GetProfileData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Validated
public class ProfileUpdatedEventData implements HttpEvent {

    @NotBlank
    private String processId;

    @NotNull
    private GetProfileData.ProfileData profileData;

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // Prevents duplication when serializing to JSON (subtype discriminator property)
    public Event getEvent() {
        return Event.PROFILE_UPDATED;
    }
}
