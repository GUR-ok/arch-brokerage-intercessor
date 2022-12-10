package ru.gur.archintercessor.interaction.claim;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.gur.archintercessor.interaction.claim.event.ClaimEventType;
import ru.gur.archintercessor.interaction.claim.request.ClaimData;
import ru.gur.archintercessor.interaction.claim.request.CreateClaimRequest;
import ru.gur.archintercessor.interaction.claim.response.ClaimDto;
import ru.gur.archintercessor.interaction.claim.response.FindClaimsResponse;
import ru.gur.archintercessor.interaction.claim.eventsender.ClaimEventSender;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClaimClientImpl implements ClaimClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final Set<ClaimEventSender> senders;

    @Value("${interaction.claim.uri}")
    private URI claimUri;

    @Override
    public List<ClaimDto> findNotCompletedClaims(final UUID profileId) {
        Assert.notNull(profileId, "profileId must not be null");

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("states", "NOT_COMPLETED");

        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(claimUri +
                "/claims/" + profileId).queryParams(params);

        final RequestEntity requestEntity =
                RequestEntity
                        .get(builder.toUriString(), params)
                        .build();

        final ResponseEntity<FindClaimsResponse> responseEntity =
                restTemplate.exchange(requestEntity, FindClaimsResponse.class);

        return Optional.ofNullable(responseEntity.getBody())
                .map(FindClaimsResponse::getClaims)
                .orElse(new ArrayList<>());
    }

    @Override
    public UUID createNewClaim(final CreateClaimRequest createClaimRequest) {
        Assert.notNull(createClaimRequest, "CreateClaimRequest must not be null");

        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(claimUri +
                "/claims");

        final RequestEntity requestEntity =
                RequestEntity
                        .post(builder.toUriString())
                        .body(createClaimRequest);
        ResponseEntity<UUID> responseEntity = restTemplate.exchange(requestEntity, UUID.class);

        return Objects.requireNonNull(responseEntity.getBody());
    }

    @Override
    public void executeEvent(final ClaimEventType claimEventType,
                             final ClaimData claimData,
                             final String processId) {
        Assert.notNull(claimEventType, "claimEventType must not be null");
        Assert.notNull(claimData, "claimData must not be null");
        Assert.hasText(processId, "processId must not be blank");

        senders.stream().filter(s -> s.canSend(claimEventType))
                .forEach(s -> s.sendEvent(claimData, processId));
    }
}
