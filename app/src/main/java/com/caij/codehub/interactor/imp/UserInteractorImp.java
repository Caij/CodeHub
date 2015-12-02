package com.caij.codehub.interactor.imp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.User;
import com.caij.codehub.interactor.InteractorCallBack;
import com.caij.codehub.interactor.UserInteractor;
import com.caij.lib.utils.VolleyManager;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Caij on 2015/9/18.
 */
public class UserInteractorImp implements UserInteractor {

    @Override
    public void getUserInfo(String token, String username, Object requestTag, final InteractorCallBack<User> interactorCallBack) {
        interactorCallBack.onLoading();
        String url = API.API_HOST + "/users/" + username;
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        GsonRequest<User> request = new GsonRequest<User>(Request.Method.GET, url, "", head, new TypeToken<User>() {}.getType(),new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {
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
