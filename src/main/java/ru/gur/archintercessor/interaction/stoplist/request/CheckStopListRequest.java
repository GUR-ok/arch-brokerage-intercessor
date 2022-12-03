package ru.gur.archintercessor.interaction.stoplist.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckStopListRequest {

    private String passportNumber;
}
