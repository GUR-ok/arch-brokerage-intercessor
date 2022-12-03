package ru.gur.archintercessor.interaction.claim;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.claim.event.ClaimEvent;
import ru.gur.archintercessor.interaction.claim.request.CreateClaimRequest;

import java.util.UUID;

@Primary
@Component
@ConditionalOnProperty(prefix = "interaction", name = "claim.stubEnabled", matchIfMissing = false)
public class ClaimClientStub implements ClaimClient {

    @Override
    public UUID createNewClaim(final CreateClaimRequest createClaimRequest) {
        System.out.println("STUB method " + Thread.currentThread().getStackTrace()[0].getMethodName() + " of class" +
                this.getClass().getSimpleName() + " called");

        return UUID.randomUUID();
    }

    @Override
    public UUID executeEvent(final ClaimEvent claimEvent) {
        System.out.println("STUB method " + Thread.currentThread().getStackTrace()[0].getMethodName() + " of class" +
                this.getClass().getSimpleName() + " called");

        return claimEvent.getId();
    }
}
