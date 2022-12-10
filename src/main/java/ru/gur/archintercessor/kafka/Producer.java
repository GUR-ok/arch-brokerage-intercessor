package ru.gur.archintercessor.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "kafka.enabled", matchIfMissing = false)
public class Producer {
    private final KafkaTemplate<String, String> kafkaTemplateString;

    public static final String NOTIFICATION_TOPIC = "brokerage-notification";
    public static final String CLAIM_TOPIC = "brokerage-claim";

    public void sendEvent(final String topic, final String key, final KafkaEvent event) throws JsonProcessingException {
        Assert.hasText(topic, "topic must not be blank");
        Assert.hasText(key, "key must not be blank");
        Assert.notNull(event, "KafkaEvent must not be null");

        final ObjectMapper objectMapper = new ObjectMapper();
        final String data = objectMapper.writeValueAsString(event);

        kafkaTemplateString.send(topic, key, data)
                .addCallback(
                        result -> log.info("Kafka send complete"),
                        fail -> log.error("Kafka fail send"));
    }
}
