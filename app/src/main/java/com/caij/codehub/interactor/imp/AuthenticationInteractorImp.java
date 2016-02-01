package com.caij.codehub.interactor.imp;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Token;
import com.caij.codehub.interactor.AuthenticationInteractor;
import com.caij.codehub.interactor.InteractorCallBack;
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
    public void login(final String username, final String pwd, final Object requestTag, final InteractorCallBack<Token> interactorCallBack) {
        try {
            interactorCallBack.onLoading();
            JSONObject json = new JSONObject();
            json.put(NOTE, API.TOKEN_NOTE);
            JSONArray jsonArray = new JSONArray(Arrays.asList(API.SCOPES));
            json.put(SCOPES, jsonArray);
            Map<String, String> head = new HashMap<>();
            addAuthorizationHead(head, username, pwd);
            GsonRequest<Token> request = new GsonRequest<Token>(Request.Method.POST, API.AUTHORIZATION_URL, json.toString(), head,
                    GsonRequest.ContentType.JSON_BODY_CONTENT_TYPE, new TypeToken<Token>(){}.getType(),
                    new Response.Listener<Token>() {
                @Override
                public void onResponse(Token response) {
                    interactorCallBack.onSuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    handlerLoginError(error, username, pwd, requestTag, uiCallBack);
                    interactorCallBack.onError(error);
                }
            });
            request.setShouldCache(false);
            VolleyManager.addRequest(request, requestTag);
        } catch (JSONException e) {
            interactorCallBack.onError(new VolleyError(e));
        }
    }

    @Override
    public void logout(String base64UsernameAndPwd, String tokenId, Object requestTag, final InteractorCallBack<NetworkResponse> interactorCallBack) {
        removeToken(base64UsernameAndPwd, tokenId, requestTag, interactorCallBack);
    }

    private void removeToken(final String base64UsernameAndPwd, String id, Object requestTag, final InteractorCallBack<NetworkResponse> interactorCallBack) {
        interactorCallBack.onLoading();
        Map<String, String> head = new HashMap<>();
        addAuthorizationHead(head, base64UsernameAndPwd);
        final NetworkResponseRequest request = new NetworkResponseRequest(Request.Method.DELETE, API.AUTHORIZATION_URL + "/" + id, "", head,
                new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                if (response.statusCode == 204) {
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
        request.setShouldCache(false);
        VolleyManager.addRequest(request, requestTag);
    }

    @Override
    public void getHaveTokens(final String username, final String pwd, Object requestTag, final InteractorCallBack<List<Token>> interactorCallBack) {
        Map<String, String> head = new HashMap<>();
        addAuthorizationHead(head, username, pwd);
        GsonRequest<List<Token>> request = new GsonRequest<List<Token>>(Request.Method.GET, API.AUTHORIZATION_URL, "", head,
                new TypeToken<List<Token>>(){}.getType(), new Response.Listener<List<Token>>() {
            @Override
            public void onResponse(List<Token> response) {
                interactorCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                interactorCallBack.onError(error);
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
