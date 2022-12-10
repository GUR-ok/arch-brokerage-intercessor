package ru.gur.archintercessor.interaction.agreement;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.agreement.request.AgreementCreationRequest;

import java.util.concurrent.ThreadLocalRandom;

@Primary
@Component
@ConditionalOnProperty(prefix = "interaction", name = "agreement.stubEnabled", matchIfMissing = false)
public class AgreementClientStub implements AgreementClient {

    @Override
    public Long createAgreement(final AgreementCreationRequest agreementCreationRequest) {
        System.out.println("STUB method " + Thread.currentThread().getStackTrace()[0].getMethodName() + " of class" +
                this.getClass().getSimpleName() + " called");

        return ThreadLocalRandom.current().nextLong(100000, 999999);
    }

    @Override
    public void cancelAgreement(final Long agreementId) {
        System.out.println("STUB method " + Thread.currentThread().getStackTrace()[0].getMethodName() + " of class" +
                this.getClass().getSimpleName() + " called");
    }
}
