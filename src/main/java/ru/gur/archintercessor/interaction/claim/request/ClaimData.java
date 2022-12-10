package ru.gur.archintercessor.interaction.claim.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ClaimData {

    private UUID id;

    private Long agreementNumber;

    private UUID brokerageAccountId;

    private String firstName;
}
