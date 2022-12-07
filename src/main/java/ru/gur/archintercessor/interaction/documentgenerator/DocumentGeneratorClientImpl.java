package ru.gur.archintercessor.interaction.documentgenerator;

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
import ru.gur.archintercessor.interaction.documentgenerator.request.PrintedFormGenerationRequest;

import java.net.URI;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocumentGeneratorClientImpl implements DocumentGeneratorClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${interaction.documentgenerator.uri}")
    private URI profileUri;

    @Override
    public String createDocument(final PrintedFormGenerationRequest printedFormGenerationRequest) {
        Assert.notNull(printedFormGenerationRequest, "printedFormGenerationRequest must not be null");


        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(profileUri +
                "/reports");

        final RequestEntity requestEntity =
                RequestEntity
                        .post(builder.toUriString())
                        .body(printedFormGenerationRequest);

        return restTemplate.exchange(requestEntity, String.class).getBody();
    }

    @Override
    public String getUrl(String reportName) {


        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", reportName);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(profileUri +
                "/reports").queryParams(params);

        final RequestEntity requestEntity =
                RequestEntity
                        .get(builder.toUriString(), String.class, params)
                        .build();

        return restTemplate.exchange(requestEntity, String.class).getBody();
    }
}
