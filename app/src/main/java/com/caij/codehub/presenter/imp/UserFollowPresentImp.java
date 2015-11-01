package com.caij.codehub.presenter.imp;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.presenter.UserFollowPresent;
import com.caij.codehub.ui.listener.UserFollowUi;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.volley.request.NetworkResponseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Caij on 2015/11/1.
 */
public class UserFollowPresentImp implements UserFollowPresent{

    private UserFollowUi mUserUi;
    private Object tag = new Object();

    public UserFollowPresentImp(UserFollowUi ui) {
        this.mUserUi = ui;
    }


    @Override
    public void checkFollowState(String token, String username) {
        String url = API.API_HOST + "/user/following/" + username;
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        NetworkResponseRequest request = new NetworkResponseRequest(Request.Method.GET, url, "", head, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                if (response != null && response.statusCode == 204) {
                    mUserUi.onCheckFollowInfoSuccess(true);
                }else {
                    mUserUi.onCheckFollowInfoSuccess(false);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null && error.networkResponse != null && error.networkResponse.statusCode == 404) {
                    mUserUi.onCheckFollowInfoSuccess(false);
                }
            }
        });

        VolleyUtil.addRequest(request, tag);
    }

    @Override
    public void followUser(String token, String username) {
        mUserUi.showLoading(LoadType.FIRSTLOAD);
        String url = API.API_HOST + "/user/following/" + username;
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        NetworkResponseRequest request = new NetworkResponseRequest(Request.Method.PUT, url, " ", head, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                if (response != null && response.statusCode == 204) {
                    mUserUi.onFollowSuccess();
                }else {
                    mUserUi.onFollowError();
                }
                mUserUi.hideLoading(LoadType.FIRSTLOAD);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUserUi.onFollowError();
                mUserUi.hideLoading(LoadType.FIRSTLOAD);
            }
        });

        VolleyUtil.addRequest(request, tag);
    }

    @Override
    public void unfollowUser(String token, String username) {
        mUserUi.showLoading(LoadType.FIRSTLOAD);
        String url = API.API_HOST + "/user/following/" + username;
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        NetworkResponseRequest request = new NetworkResponseRequest(Request.Method.DELETE, url, " ", head, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                if (response != null && response.statusCode == 204) {
                    mUserUi.onUnfollowSuccess();
                }else {
                    mUserUi.onUnfollowError();
                }
                mUserUi.hideLoading(LoadType.FIRSTLOAD);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUserUi.onUnfollowError();
                mUserUi.hideLoading(LoadType.FIRSTLOAD);
            }
        });

        VolleyUtil.addRequest(request, tag);
    }


    @Override
    public void onDeath() {
        VolleyUtil.cancelRequestByTag(tag);
    }
}
