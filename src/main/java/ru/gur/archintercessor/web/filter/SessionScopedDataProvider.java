package ru.gur.archintercessor.web.filter;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Data
@Component
@SessionScope
public class SessionScopedDataProvider {

    private UUID profileId;
}
