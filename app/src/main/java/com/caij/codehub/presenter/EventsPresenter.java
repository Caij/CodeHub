package com.caij.codehub.presenter;

import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.event.EventWrap;
import com.caij.codehub.ui.callback.UiCallBack;

import java.util.List;

/**
 * Created by Caij on 2015/9/24.
 */
public interface EventsPresenter extends Present {

    public void getReceivedEvents(String username, String token, Page page, Object requestTag, UiCallBack<List<EventWrap>> uiCallBack);

}
