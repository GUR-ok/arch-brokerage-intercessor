package ru.gur.archintercessor.interaction.profile.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileInfo {

    private String firstName;

    private String lastName;

    private String passportNumber;
}
