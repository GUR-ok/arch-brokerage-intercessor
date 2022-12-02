package ru.gur.archintercessor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gur.archintercessor.service.handler.EventHandler;
import ru.gur.archintercessor.web.request.EventSource;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class EventHandlerConfig {

    @Bean
    <T extends EventSource> Set<EventHandler<T>> eventHandlers(Set<EventHandler<T>> eventHandlers) {
        return new HashSet<>(eventHandlers);
    }
}
