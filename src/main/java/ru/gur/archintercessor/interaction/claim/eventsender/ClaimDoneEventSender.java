package ru.gur.archintercessor.interaction.claim.eventsender;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.gur.archintercessor.interaction.claim.event.ClaimEventType;
import ru.gur.archintercessor.interaction.claim.request.ClaimData;
import ru.gur.archintercessor.interaction.claim.request.UpdateClaimRequest;
import ru.gur.archintercessor.kafka.ClaimDoneEventData;
import ru.gur.archintercessor.kafka.Producer;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClaimDoneEventSender implements ClaimEventSender {

    private final Producer producer;

    @Override
    public boolean canSend(final ClaimEventType claimEventType) {
        Assert.notNull(claimEventType, "ClaimEventType must not be null");

        return ClaimEventType.DONE.equals(claimEventType);
    }

    @Override
    public void sendEvent(final ClaimData claimData, final String processId) {
        final ClaimDoneEventData claimDoneEventData =
                ClaimDoneEventData.builder()
                        .id(claimData.getId())
                        .updateClaimRequest(UpdateClaimRequest.builder()
                                .agreementNumber(claimData.getAgreementNumber())
                                .brokerageAccountId(claimData.getBrokerageAccountId())
                                .firstName(claimData.getFirstName())
                                .build())
                        .build();

        try {
            producer.sendEvent(Producer.CLAIM_TOPIC, processId,
                    claimDoneEventData);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
