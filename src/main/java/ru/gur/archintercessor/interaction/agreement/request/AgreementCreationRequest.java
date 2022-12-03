package ru.gur.archintercessor.interaction.agreement.request;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AgreementCreationRequest {

    private UUID claimId;

    private String profileId;

    private String productId;
}
