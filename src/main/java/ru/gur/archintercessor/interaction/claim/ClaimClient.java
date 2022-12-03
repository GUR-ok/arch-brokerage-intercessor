package ru.gur.archintercessor.interaction.claim;

import ru.gur.archintercessor.interaction.claim.event.ClaimEvent;
import ru.gur.archintercessor.interaction.claim.request.CreateClaimRequest;

import java.util.UUID;

public interface ClaimClient {

    UUID createNewClaim(CreateClaimRequest createClaimRequest);

    UUID executeEvent(ClaimEvent claimEvent);
}
