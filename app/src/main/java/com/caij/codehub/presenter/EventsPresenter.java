package com.caij.codehub.presenter;

import com.caij.codehub.bean.Page;
import com.caij.codehub.ui.listener.EventsUi;

/**
 * Created by Caij on 2015/9/24.
 */
public interface EventsPresenter extends BasePresent<EventsUi>{

    public void getReceivedEvents(String username, String token, int loadType, Page page);

}
