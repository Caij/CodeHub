package com.caij.codehub.interactor.imp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.event.EventWrap;
import com.caij.codehub.interactor.EventsInteractor;
import com.caij.codehub.interactor.InteractorCallBack;
import com.caij.codehub.request.EventRequest;
import com.caij.lib.utils.VolleyManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Caij on 2015/9/24.
 */
public class EventsInteractorImp implements EventsInteractor {
    @Override
    public void getReceivedEvents(String username, String token, Page page, Object requestTag, final InteractorCallBack<List<EventWrap>> interactorCallBack) {
        interactorCallBack.onLoading();
        String url = API.API_HOST + "/users/" + username + "/received_events";
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        Map<String, String> params = new HashMap<>();
        params.put(API.PAGE, String.valueOf(page.getPageIndex()));
        params.put(API.PER_PAGE, String.valueOf(page.getPageDataCount()));
        EventRequest request = new EventRequest(Request.Method.GET, url, params, head, new Response.Listener<List<EventWrap>>() {
            @Override
            public void onResponse(List<EventWrap> response) {
               interactorCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               interactorCallBack.onError(error);
            }
        });
        VolleyManager.addRequest(request, requestTag);
    }
}
