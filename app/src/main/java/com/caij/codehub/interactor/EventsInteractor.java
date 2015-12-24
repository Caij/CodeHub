package com.caij.codehub.interactor;

import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.event.Event;
import com.caij.codehub.bean.event.EventWrap;

import java.util.List;

/**
 * Created by Caij on 2015/9/24.
 */
public interface EventsInteractor extends Interactor {

    public void getReceivedEvents(String username, String token, Page page, Object requestTag, InteractorCallBack<List<Event>> interactorCallBack);

}
