package com.caij.codehub.interactor.imp;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.interactor.FollowActionInteractor;
import com.caij.codehub.interactor.InteractorCallBack;
import com.caij.lib.utils.VolleyManager;
import com.caij.lib.volley.request.NetworkResponseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Caij on 2015/11/1.
 */
public class FollowActionInteractorImp implements FollowActionInteractor {

    @Override
    public void getFollowState(String token, String username, Object requestTag, final InteractorCallBack<Boolean> interactorCallBack) {
        interactorCallBack.onLoading();
        String url = API.API_HOST + "/user/following/" + username;
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        NetworkResponseRequest request = new NetworkResponseRequest(Request.Method.GET, url, "", head, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                if (response != null && response.statusCode == 204) {
                    interactorCallBack.onSuccess(true);
                }else {
                    interactorCallBack.onSuccess(false);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null && error.networkResponse != null && error.networkResponse.statusCode == 404) {
                    interactorCallBack.onSuccess(false);
                }else {
                    interactorCallBack.onError(error);
                }
            }
        });

        VolleyManager.addRequest(request, requestTag);
    }

    @Override
    public void followUser(String token, String username, Object requestTag, final InteractorCallBack<NetworkResponse> interactorCallBack) {
        interactorCallBack.onLoading();
        String url = API.API_HOST + "/user/following/" + username;
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        NetworkResponseRequest request = new NetworkResponseRequest(Request.Method.PUT, url, " ", head, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                if (response != null && response.statusCode == 204) {
                    interactorCallBack.onSuccess(response);
                }else {
                    interactorCallBack.onError(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                interactorCallBack.onError(error);
            }
        });

        VolleyManager.addRequest(request, requestTag);
    }

    @Override
    public void unfollowUser(String token, String username, Object requestTag, final InteractorCallBack<NetworkResponse> interactorCallBack) {
        interactorCallBack.onLoading();
        String url = API.API_HOST + "/user/following/" + username;
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        NetworkResponseRequest request = new NetworkResponseRequest(Request.Method.DELETE, url, " ", head, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                if (response != null && response.statusCode == 204) {
                    interactorCallBack.onSuccess(response);
                }else {
                    interactorCallBack.onError(null);
                }
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
