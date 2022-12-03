package ru.gur.archintercessor.web.filter;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.UUID;

@Data
@Component
@RequestScope
public class RequestScopedDataProvider {

    private UUID profileId;
}
