package ru.gur.archintercessor.interaction.claim.event;

import java.util.UUID;

public interface ClaimEvent {

        ClaimEventType getEvent();

        UUID getId();
}
