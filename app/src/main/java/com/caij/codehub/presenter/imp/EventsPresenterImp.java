package com.caij.codehub.presenter.imp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.event.EventWrap;
import com.caij.codehub.presenter.EventsPresenter;
import com.caij.codehub.request.EventRequest;
import com.caij.codehub.ui.listener.EventsUi;
import com.caij.lib.utils.VolleyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Caij on 2015/9/24.
 */
public class EventsPresenterImp implements EventsPresenter {

    private Object tag = new Object();
    EventsUi mUi;

    public EventsPresenterImp(EventsUi ui) {
        this.mUi = ui;
    }

    @Override
    public void getReceivedEvents(String username, String token, final int loadType, Page page) {
        mUi.showLoading(loadType);
        String url = API.API_HOST + "/users/" + username + "/received_events";
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        Map<String, String> params = new HashMap<>();
        params.put(API.PAGE, String.valueOf(page.getPageIndex()));
        params.put(API.PER_PAGE, String.valueOf(page.getPageDataCount()));
        EventRequest request = new EventRequest(Request.Method.GET, url, params, head,new Response.Listener<List<EventWrap>>() {
            @Override
            public void onResponse(List<EventWrap> response) {
                handlerResponse(loadType, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handlerError(loadType, error);
            }
        });
        VolleyUtil.addRequest(request, tag);
    }
    private void handlerError(int loadType, VolleyError error) {
        mUi.hideLoading(loadType);
        mUi.showError(loadType, error);
    }

    private void handlerResponse(int loadType,List<EventWrap> newses) {
        mUi.hideLoading(loadType);
        if (loadType == LoadType.REFRESH || loadType == LoadType.FIRSTLOAD) {
            mUi.onGetNewsSuccess(newses);
        }else if (loadType == LoadType.LOADMOER) {
            mUi.onLoadMoreSuccess(newses);
        }
    }

    @Override
    public void onDeath() {
        VolleyUtil.cancelRequestByTag(tag);
    }
}