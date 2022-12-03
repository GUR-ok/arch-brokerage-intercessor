package ru.gur.archintercessor.interaction.agreement;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.agreement.request.AgreementCreationRequest;

import java.util.Random;

@Primary
@Component
@ConditionalOnProperty(prefix = "interaction", name = "agreement.stubEnabled", matchIfMissing = false)
public class AgreementClientStub implements AgreementClient {

    @Override
    public Long createAgreement(final AgreementCreationRequest agreementCreationRequest) {
        System.out.println("STUB method " + Thread.currentThread().getStackTrace()[0].getMethodName() + " of class" +
                this.getClass().getSimpleName() + " called");

        final Random random = new Random();
        return random.nextLong();
    }

    @Override
    public void cancelAgreement(final Long agreementId) {
        System.out.println("STUB method " + Thread.currentThread().getStackTrace()[0].getMethodName() + " of class" +
                this.getClass().getSimpleName() + " called");
    }
}
