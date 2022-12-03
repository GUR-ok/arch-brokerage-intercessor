package ru.gur.archintercessor.interaction.stoplist;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.stoplist.request.CheckStopListRequest;

import java.util.Random;

@Primary
@Component
@ConditionalOnProperty(prefix = "interaction", name = "stoplist.stubEnabled", matchIfMissing = false)
public class StopListClientStub implements StopListClient {

    @Override
    public boolean checkStopList(final CheckStopListRequest checkStopListRequest) {
        System.out.println("STUB method " + Thread.currentThread().getStackTrace()[0].getMethodName() + " of class" +
                this.getClass().getSimpleName() + " called");

        final Random random = new Random();
        return random.nextBoolean();
    }
}
