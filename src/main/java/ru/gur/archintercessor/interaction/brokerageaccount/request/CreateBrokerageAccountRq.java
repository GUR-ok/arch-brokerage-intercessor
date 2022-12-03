package ru.gur.archintercessor.interaction.brokerageaccount.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateBrokerageAccountRq {

    private Long agreementNumber;
}
