package ru.gur.archintercessor.interaction.claim.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateClaimRequest {

    private String processId;

    private UUID profileId;
}
