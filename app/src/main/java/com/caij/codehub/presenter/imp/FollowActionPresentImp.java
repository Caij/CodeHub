package com.caij.codehub.presenter.imp;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.presenter.FollowActionPresent;
import com.caij.codehub.ui.callback.UiCallBack;
import com.caij.lib.utils.VolleyManager;
import com.caij.lib.volley.request.NetworkResponseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Caij on 2015/11/1.
 */
public class FollowActionPresentImp implements FollowActionPresent {

    @Override
    public void checkFollowState(String token, String username, Object requestTag, final UiCallBack<Boolean> uiCallBack) {
        uiCallBack.onLoading();
        String url = API.API_HOST + "/user/following/" + username;
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        NetworkResponseRequest request = new NetworkResponseRequest(Request.Method.GET, url, "", head, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                if (response != null && response.statusCode == 204) {
                    uiCallBack.onSuccess(true);
                }else {
                    uiCallBack.onSuccess(false);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null && error.networkResponse != null && error.networkResponse.statusCode == 404) {
                    uiCallBack.onSuccess(false);
                }else {
                    uiCallBack.onError(error);
                }
            }
        });

        VolleyManager.addRequest(request, requestTag);
    }

    @Override
    public void followUser(String token, String username, Object requestTag, final UiCallBack<NetworkResponse> uiCallBack) {
        uiCallBack.onLoading();
        String url = API.API_HOST + "/user/following/" + username;
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        NetworkResponseRequest request = new NetworkResponseRequest(Request.Method.PUT, url, " ", head, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                if (response != null && response.statusCode == 204) {
                    uiCallBack.onSuccess(response);
                }else {
                    uiCallBack.onError(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                uiCallBack.onError(error);
            }
        });

        VolleyManager.addRequest(request, requestTag);
    }

    @Override
    public void unfollowUser(String token, String username, Object requestTag, final UiCallBack<NetworkResponse> uiCallBack) {
        uiCallBack.onLoading();
        String url = API.API_HOST + "/user/following/" + username;
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        NetworkResponseRequest request = new NetworkResponseRequest(Request.Method.DELETE, url, " ", head, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                if (response != null && response.statusCode == 204) {
                    uiCallBack.onSuccess(response);
                }else {
                    uiCallBack.onError(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               uiCallBack.onError(error);
            }
        });

        VolleyManager.addRequest(request, requestTag);
    }
}
