package ru.gur.archintercessor.interaction.profile;

import ru.gur.archintercessor.interaction.profile.response.ProfileInfo;

import java.util.UUID;

public interface ProfileClient {

    ProfileInfo getProfileInfo(UUID profileId);
}
