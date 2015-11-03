package com.caij.codehub.presenter.imp;


import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.Base64;
import com.caij.codehub.bean.Token;
import com.caij.codehub.presenter.LoginPresenter;
import com.caij.codehub.ui.listener.LoginUi;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.volley.request.StringRequest;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/25.
 */

public class LoginPresenterImp implements LoginPresenter {

    private final static String SCOPES = "scopes";
    private final static String NOTE = "note";

    private LoginUi mUi;
    private Object tag = new Object();

    public LoginPresenterImp(LoginUi ui) {
        this.mUi = ui;
    }

    @Override
    public void login(final String username, final String pwd) {
        mUi.onLoading();
        try {
            JSONObject json = new JSONObject();
            json.put(NOTE, API.TOKEN_NOTE);
            JSONArray jsonArray = new JSONArray(Arrays.asList(API.SCOPES));
            json.put(SCOPES, jsonArray);
            Map<String, String> head = new HashMap<>();
            addAuthorizationHead(head, username, pwd);
            GsonRequest<Token> request = new GsonRequest<Token>(Request.Method.POST, API.AUTHORIZATION_URL, json.toString(), head,
                    GsonRequest.JSON_BODY_CONTENT_TYPE, new TypeToken<Token>(){}.getType(),
                    new Response.Listener<Token>() {
                @Override
                public void onResponse(Token response) {
                    mUi.onLoaded();
                    mUi.onLoginSuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    handlerLoginError(error, username, pwd);
                }
            });
            VolleyUtil.addRequest(request, tag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void handlerLoginError(VolleyError error, String username, String pwd) {
        if (error instanceof ServerError) {
            NetworkResponse response = ((ServerError) error).networkResponse;
            if (response != null) {
                int statusCode = response.statusCode;
                if (statusCode == 422) {
                    removeTokenIfHaveToken(username, pwd);
                }else {
                    mUi.onLoaded();
                    mUi.onLoginError(error);
                }
            }
        }else {
            mUi.onLoaded();
            mUi.onLoginError(error);
        }
    }

    private void removeToken(final String username, final String pwd, String id) {
        Map<String, String> head = new HashMap<>();
        addAuthorizationHead(head, username, pwd);
        final StringRequest request = new StringRequest(Request.Method.DELETE, API.AUTHORIZATION_URL + "/" + id, "", head,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                login(username, pwd);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUi.onLoaded();
                mUi.onLoginError(error);
            }
        });
        VolleyUtil.addRequest(request, tag);
    }

    public void removeTokenIfHaveToken(final String username, final String pwd) {
        Map<String, String> head = new HashMap<>();
        addAuthorizationHead(head, username, pwd);
        GsonRequest<List<Token>> request = new GsonRequest<List<Token>>(Request.Method.GET, API.AUTHORIZATION_URL, "", head,
                new TypeToken<List<Token>>(){}.getType(), new Response.Listener<List<Token>>() {
            @Override
            public void onResponse(List<Token> response) {
                for (Token token : response) {
                    if (token != null && API.TOKEN_NOTE.equals(token.getNote())) {
                        removeToken(username, pwd, String.valueOf(token.getId()));
                        break;
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUi.onLoaded();
                mUi.onLoginError(error);
            }
        });
        VolleyUtil.addRequest(request, tag);
    }

    private static Map<String, String> addAuthorizationHead(Map<String, String> head, String username, final String pwd) {
        head.put(API.AUTHORIZATION, "Basic " + Base64.encode(username + ":" + pwd));
        return head;
    }

    @Override
    public void onDeath() {
        VolleyUtil.cancelRequestByTag(tag);
    }
}
