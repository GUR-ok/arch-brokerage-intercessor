package ru.gur.archintercessor.interaction.profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.gur.archintercessor.interaction.profile.response.ProfileInfo;
import ru.gur.archintercessor.kafka.Producer;

import java.net.URI;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProfileClientImpl implements ProfileClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${interaction.profile.uri}")
    private URI profileUri;

    @Override
    public ProfileInfo getProfileInfo(final UUID profileId) {
        Assert.notNull(profileId, "profileId must not be null");

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(profileUri +
                "/profiles/info/" + profileId); // The allRequestParams must have been built for all the query params

        final RequestEntity requestEntity =
                RequestEntity
                        .get(builder.toUriString(), String.class)
                        .build();
        log.info("ProfileInfo get request: {}", requestEntity);

        return restTemplate.exchange(requestEntity, ProfileInfo.class).getBody();
    }
}
