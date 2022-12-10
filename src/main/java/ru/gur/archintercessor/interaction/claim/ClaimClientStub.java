package ru.gur.archintercessor.interaction.claim;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.claim.event.ClaimEventType;
import ru.gur.archintercessor.interaction.claim.request.ClaimData;
import ru.gur.archintercessor.interaction.claim.request.CreateClaimRequest;
import ru.gur.archintercessor.interaction.claim.response.ClaimDto;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Primary
@Component
@ConditionalOnProperty(prefix = "interaction", name = "claim.stubEnabled", matchIfMissing = false)
public class ClaimClientStub implements ClaimClient {

    @Override
    public List<ClaimDto> findNotCompletedClaims(final UUID profileId) {
        System.out.println("STUB method " + Thread.currentThread().getStackTrace()[0].getMethodName() + " of class" +
                this.getClass().getSimpleName() + " called");

        return Collections.emptyList();
    }

    @Override
    public UUID createNewClaim(final CreateClaimRequest createClaimRequest) {
        System.out.println("STUB method " + Thread.currentThread().getStackTrace()[0].getMethodName() + " of class" +
                this.getClass().getSimpleName() + " called");

        return UUID.randomUUID();
    }

    @Override
    public void executeEvent(final ClaimEventType claimEventType,
                             final ClaimData claimData,
                             final String processId) {
        System.out.println("STUB method " + Thread.currentThread().getStackTrace()[0].getMethodName() + " of class" +
                this.getClass().getSimpleName() + " called");
    }
}
