package ru.gur.archintercessor.interaction.claim.response;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ClaimDto {

    UUID id;

    UUID profileId;

    String processId;

    String state;
}
