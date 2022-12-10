package ru.gur.archintercessor.interaction.stoplist;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.stoplist.request.CheckStopListRequest;

@Primary
@Component
@ConditionalOnProperty(prefix = "interaction", name = "stoplist.stubEnabled", matchIfMissing = false)
public class StopListClientStub implements StopListClient {

    @Override
    public boolean checkStopList(final CheckStopListRequest checkStopListRequest) {
        System.out.println("STUB method " + Thread.currentThread().getStackTrace()[0].getMethodName() + " of class" +
                this.getClass().getSimpleName() + " called");

        return !checkStopListRequest.getPassportNumber().equals("123456");
    }
}
