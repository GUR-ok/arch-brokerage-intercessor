package ru.gur.archintercessor.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventProcessingResult {

    private String processId;
}
