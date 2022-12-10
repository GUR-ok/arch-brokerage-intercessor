package ru.gur.archintercessor.interaction.claim.eventsender;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.gur.archintercessor.interaction.claim.event.ClaimEventType;
import ru.gur.archintercessor.interaction.claim.request.ClaimData;
import ru.gur.archintercessor.kafka.ClaimInProgressEventData;
import ru.gur.archintercessor.kafka.Producer;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClaimInProgressEventSender implements ClaimEventSender {

    private final Producer producer;

    @Override
    public boolean canSend(final ClaimEventType claimEventType) {
        Assert.notNull(claimEventType, "ClaimEventType must not be null");

        return ClaimEventType.IN_PROGRESS.equals(claimEventType);
    }

    @Override
    public void sendEvent(final ClaimData claimData, final String processId) {
        final ClaimInProgressEventData claimInProgressEventData =
                ClaimInProgressEventData.builder()
                        .id(claimData.getId())
                        .build();

        try {
            producer.sendEvent(Producer.CLAIM_TOPIC, processId,
                    claimInProgressEventData);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
