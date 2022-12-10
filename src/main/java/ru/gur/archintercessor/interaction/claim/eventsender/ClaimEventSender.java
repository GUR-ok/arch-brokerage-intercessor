package ru.gur.archintercessor.interaction.claim.eventsender;

import ru.gur.archintercessor.interaction.claim.event.ClaimEventType;
import ru.gur.archintercessor.interaction.claim.request.ClaimData;

public interface ClaimEventSender {

    boolean canSend(ClaimEventType claimEventType);

    void sendEvent(ClaimData claimData, String processId);
}
