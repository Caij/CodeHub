package com.caij.codehub.interactor.imp;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Token;
import com.caij.codehub.interactor.AuthenticationInteractor;
import com.caij.codehub.ui.callback.UiCallBack;
import com.caij.codehub.utils.Base64;
import com.caij.lib.utils.VolleyManager;
import com.caij.lib.volley.request.NetworkResponseRequest;
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
 * Created by Caij on 2015/8/25.
 */

public class AuthenticationInteractorImp implements AuthenticationInteractor {

    private final static String SCOPES = "scopes";
    private final static String NOTE = "note";

    @Override
    public void login(final String username, final String pwd, final Object requestTag, final UiCallBack<Token> uiCallBack) {
        try {
            uiCallBack.onLoading();
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
                    uiCallBack.onSuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    handlerLoginError(error, username, pwd, requestTag, uiCallBack);
                    uiCallBack.onError(error);
                }
            });
            request.setShouldCache(false);
            VolleyManager.addRequest(request, requestTag);
        } catch (JSONException e) {
            uiCallBack.onError(new VolleyError(e));
        }
    }

    @Override
    public void logout(String base64UsernameAndPwd, String tokenId, Object requestTag, final UiCallBack<NetworkResponse> uiCallBack) {
        removeToken(base64UsernameAndPwd, tokenId, requestTag, uiCallBack);
    }

    private void handlerLoginError(VolleyError error, String username, String pwd, Object requestTag, final UiCallBack<Token> uiCallBack) {
        if (error instanceof ServerError) {
            NetworkResponse response = ((ServerError) error).networkResponse;
            if (response != null) {
                int statusCode = response.statusCode;
                if (statusCode == 422) {
                    removeTokenByLogin(username, pwd, requestTag, uiCallBack);
                }else {
                    uiCallBack.onError(error);
                }
            }
        }else {
            uiCallBack.onError(error);
        }
    }

    private void removeTokenByLogin(final String username, final String pwd, final Object requestTag, final UiCallBack<Token> uiCallBack) {
        getHaveTokens(username, pwd, requestTag, new UiCallBack<List<Token>>() {
            @Override
            public void onSuccess(List<Token> tokens) {
                for (Token token : tokens) {
                    if (token != null && API.TOKEN_NOTE.equals(token.getNote())) {
                        removeToken(Base64.encode(username + ":" + pwd), String.valueOf(token.getId()), requestTag, new UiCallBack<NetworkResponse>() {
                            @Override
                            public void onSuccess(NetworkResponse networkResponse) {
                                login(username, pwd, requestTag, uiCallBack);
                            }

                            @Override
                            public void onLoading() {}

                            @Override
                            public void onError(VolleyError error) {
                                uiCallBack.onError(error);
                            }
                        });
                        break;
                    }
                }
            }

            @Override
            public void onLoading() {}

            @Override
            public void onError(VolleyError error) {
                uiCallBack.onError(error);
            }
        });
    }

    public void removeToken(final String base64UsernameAndPwd, String id, Object requestTag, final UiCallBack<NetworkResponse> uiCallBack) {
        uiCallBack.onLoading();
        Map<String, String> head = new HashMap<>();
        addAuthorizationHead(head, base64UsernameAndPwd);
        final NetworkResponseRequest request = new NetworkResponseRequest(Request.Method.DELETE, API.AUTHORIZATION_URL + "/" + id, "", head,
                new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                if (response.statusCode == 204) {
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
        request.setShouldCache(false);
        VolleyManager.addRequest(request, requestTag);
    }

    public void getHaveTokens(final String username, final String pwd, Object requestTag, final UiCallBack<List<Token>> uiCallBack) {
        Map<String, String> head = new HashMap<>();
        addAuthorizationHead(head, username, pwd);
        GsonRequest<List<Token>> request = new GsonRequest<List<Token>>(Request.Method.GET, API.AUTHORIZATION_URL, "", head,
                new TypeToken<List<Token>>(){}.getType(), new Response.Listener<List<Token>>() {
            @Override
            public void onResponse(List<Token> response) {
                uiCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                uiCallBack.onError(error);
            }
        });
        request.setShouldCache(false);
        VolleyManager.addRequest(request, requestTag);
    }

    private static Map<String, String> addAuthorizationHead(Map<String, String> head, String base64UsernameAndPwd) {
        head.put(API.AUTHORIZATION, "Basic " + base64UsernameAndPwd);
        return head;
    }

    private static Map<String, String> addAuthorizationHead(Map<String, String> head, String username, final String pwd) {
        head.put(API.AUTHORIZATION, "Basic " + Base64.encode(username + ":" + pwd));
        return head;
    }

}
