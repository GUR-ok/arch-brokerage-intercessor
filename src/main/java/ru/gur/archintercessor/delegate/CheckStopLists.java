package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.stoplist.StopListClient;
import ru.gur.archintercessor.interaction.stoplist.request.CheckStopListRequest;
import ru.gur.archintercessor.process.VariableKey;

@Component
@RequiredArgsConstructor
public class CheckStopLists extends AbstractCommonDelegate {

    private final StopListClient stopListClient;

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        final GetProfileData.ProfileData profileData
                = (GetProfileData.ProfileData) delegateExecution.getVariable(VariableKey.PROFILE_DATA.name());

        final CheckStopListRequest request = CheckStopListRequest.builder()
                .passportNumber(profileData.getPassportNumber())
                .build();
        final boolean checkRes = stopListClient.checkStopList(request);
        delegateExecution.setVariable(VariableKey.STOPLIST_FAIL.name(), checkRes);
    }
}
