package com.caij.codehub.present;

import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.event.Event;
import com.caij.codehub.bean.event.EventWrap;
import com.caij.codehub.interactor.EventsInteractor;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.present.ui.ListUi;

import java.util.List;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/16.
 */
public class EventsPresent extends ListPresent<ListUi<Event>, Event>{

    private EventsInteractor mEventsInteractor;

    public EventsPresent(ListUi<Event> ui) {
        super(ui);
        mEventsInteractor = InteractorFactory.newInteractorInstance(EventsInteractor.class);
    }

    public void getReceivedEvents(final LoadType loadType, String username, String token, Page page) {
        mEventsInteractor.getReceivedEvents(username, token, page, this, new DefaultInteractorCallback<List<Event>>(mUi) {
            @Override
            public void onError(int msgId) {
                defaultDealWithError(msgId, loadType);
            }

            @Override
            public void onSuccess(List<Event> eventWraps) {
                defaultDealWithSuccess(eventWraps, loadType);
            }

            @Override
            public void onLoading() {
                defaultDealWithLoading(loadType);
            }
        });
    }

}
