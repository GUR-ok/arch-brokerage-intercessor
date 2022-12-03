package ru.gur.archintercessor.delegate;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.profile.ProfileClient;
import ru.gur.archintercessor.interaction.profile.response.ProfileInfo;
import ru.gur.archintercessor.process.VariableKey;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetProfileData extends AbstractRetryableDelegate {

    private final ProfileClient profileClient;

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        final UUID profileID = (UUID) delegateExecution.getVariable(VariableKey.PROFILE_ID.name());
        final ProfileInfo profileInfo = profileClient.getProfileInfo(profileID);

        delegateExecution.setVariable(VariableKey.PROFILE_DATA.name(), ProfileData.builder()
                .name(Strings.concat(profileInfo.getFirstName(), profileInfo.getLastName()))
                .passportNumber(profileInfo.getPassportNumber())
                .build());
    }

    @Data
    @Builder
    public static class ProfileData {

        private String name;

        private String passportNumber;
    }
}
