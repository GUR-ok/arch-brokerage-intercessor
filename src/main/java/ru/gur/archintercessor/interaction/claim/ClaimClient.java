package ru.gur.archintercessor.interaction.claim;

import ru.gur.archintercessor.interaction.claim.event.ClaimEventType;
import ru.gur.archintercessor.interaction.claim.request.ClaimData;
import ru.gur.archintercessor.interaction.claim.request.CreateClaimRequest;
import ru.gur.archintercessor.interaction.claim.response.ClaimDto;

import java.util.List;
import java.util.UUID;

public interface ClaimClient {

    List<ClaimDto> findNotCompletedClaims(UUID profileId);

    UUID createNewClaim(CreateClaimRequest createClaimRequest);

    void executeEvent(ClaimEventType claimEventType, ClaimData claimData, String processId);
}
