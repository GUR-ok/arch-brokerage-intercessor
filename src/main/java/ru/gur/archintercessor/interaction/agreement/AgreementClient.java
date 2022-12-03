package ru.gur.archintercessor.interaction.agreement;

import ru.gur.archintercessor.interaction.agreement.request.AgreementCreationRequest;

public interface AgreementClient {

    Long createAgreement(AgreementCreationRequest agreementCreationRequest);

    void cancelAgreement(Long agreementId);
}
