package ru.gur.archintercessor.interaction.brokerageaccount;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.brokerageaccount.request.CreateBrokerageAccountRq;

import java.util.UUID;

@Primary
@Component
@ConditionalOnProperty(prefix = "interaction", name = "brokerageaccount.stubEnabled", matchIfMissing = false)
public class BrokerageAccountClientStub implements BrokerageAccountClient{

    @Override
    public UUID createAccount(final CreateBrokerageAccountRq createBrokerageAccountRq) {
        System.out.println("STUB method " + Thread.currentThread().getStackTrace()[0].getMethodName() + " of class" +
                this.getClass().getSimpleName() + " called");

        return UUID.randomUUID();
    }
}
