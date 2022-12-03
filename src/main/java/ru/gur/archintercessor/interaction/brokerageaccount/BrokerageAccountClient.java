package ru.gur.archintercessor.interaction.brokerageaccount;

import ru.gur.archintercessor.interaction.brokerageaccount.request.CreateBrokerageAccountRq;

import java.util.UUID;

public interface BrokerageAccountClient {

    UUID createAccount(CreateBrokerageAccountRq createBrokerageAccountRq);
}
