package com.caij.codehub.presenter.imp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.event.EventWrap;
import com.caij.codehub.presenter.EventsPresenter;
import com.caij.codehub.request.EventRequest;
import com.caij.codehub.ui.callback.UiCallBack;
import com.caij.lib.utils.VolleyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Caij on 2015/9/24.
 */
public class EventsPresenterImp implements EventsPresenter {
    @Override
    public void getReceivedEvents(String username, String token, Page page, Object requestTag, final UiCallBack<List<EventWrap>> uiCallBack) {
        uiCallBack.onLoading();
        String url = API.API_HOST + "/users/" + username + "/received_events";
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        Map<String, String> params = new HashMap<>();
        params.put(API.PAGE, String.valueOf(page.getPageIndex()));
        params.put(API.PER_PAGE, String.valueOf(page.getPageDataCount()));
        EventRequest request = new EventRequest(Request.Method.GET, url, params, head,new Response.Listener<List<EventWrap>>() {
            @Override
            public void onResponse(List<EventWrap> response) {
               uiCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               uiCallBack.onError(error);
            }
        });
        VolleyUtil.addRequest(request, requestTag);
    }
}
