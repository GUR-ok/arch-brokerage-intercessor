package ru.gur.archintercessor.interaction.brokerageaccount;

import ru.gur.archintercessor.interaction.brokerageaccount.request.CreateBrokerageAccount;

import java.util.UUID;

public interface BrokerageAccountClient {

    UUID createAgreement(CreateBrokerageAccount createBrokerageAccount);
}
