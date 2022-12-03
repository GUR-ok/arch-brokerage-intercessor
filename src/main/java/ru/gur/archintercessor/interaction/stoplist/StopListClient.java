package ru.gur.archintercessor.interaction.stoplist;

import ru.gur.archintercessor.interaction.stoplist.request.CheckStopListRequest;

public interface StopListClient {

    boolean checkStopList(CheckStopListRequest checkStopListRequest);
}
