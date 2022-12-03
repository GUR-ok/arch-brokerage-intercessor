package ru.gur.archintercessor.delegate;

import lombok.Builder;
import lombok.Data;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.variables.VariableKey;

@Component
public class GetProfileData extends AbstractCommonDelegate {

    @Override
    protected void doExecute(DelegateExecution delegateExecution) {
        final ProfileData profileData = ProfileData.builder().build();

        delegateExecution.setVariable(VariableKey.PROFILE_DATA.name(), profileData);
    }

    @Data
    @Builder
    public static class ProfileData {

        private String name;

        private String passportNumber;
    }
}
