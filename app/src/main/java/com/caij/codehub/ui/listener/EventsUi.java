package com.caij.codehub.ui.listener;

import com.caij.codehub.bean.event.Event;
import com.caij.codehub.bean.event.EventWrap;

import java.util.List;

/**
 * Created by Caij on 2015/9/24.
 */
public interface EventsUi extends BaseUi{

    public void onGetNewsSuccess(List<EventWrap> newses);

    public void onLoadMoreSuccess(List<EventWrap> newses);
}
