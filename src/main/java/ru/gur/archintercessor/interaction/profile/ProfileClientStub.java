package ru.gur.archintercessor.interaction.profile;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.profile.response.ProfileInfo;

import java.util.UUID;

@Primary
@Component
@ConditionalOnProperty(prefix = "interaction", name = "profile.stubEnabled", matchIfMissing = false)
public class ProfileClientStub implements ProfileClient {

    @Override
    public ProfileInfo getProfileInfo(final UUID profileId) {
        System.out.println("STUB method " + Thread.currentThread().getStackTrace()[0].getMethodName() + " of class" +
                this.getClass().getSimpleName() + " called");

        return ProfileInfo.builder()
                .firstName(RandomStringUtils.randomAlphabetic(5))
                .lastName(RandomStringUtils.randomAlphabetic(10))
                .passportNumber(RandomStringUtils.randomNumeric(6))
                .build();
    }
}
