package ru.gur.archintercessor.delegate;

import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.process.VariableKey;

@Component
public class CheckProfile extends AbstractCommonDelegate {

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        final GetProfileData.ProfileData profileData
                = (GetProfileData.ProfileData) delegateExecution.getVariable(VariableKey.PROFILE_DATA.name());

        if (StringUtils.isBlank(profileData.getName()) || StringUtils.isBlank(profileData.getPassportNumber())) {
            delegateExecution.setVariable(VariableKey.PROFILE_CHECK_RESULT.name(), false);
        } else {
            delegateExecution.setVariable(VariableKey.PROFILE_CHECK_RESULT.name(), true);
        }
    }
}
