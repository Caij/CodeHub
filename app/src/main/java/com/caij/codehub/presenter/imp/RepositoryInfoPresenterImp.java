package com.caij.codehub.presenter.imp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.presenter.RepositoryInfoPresenter;
import com.caij.codehub.ui.callback.UiCallBack;
import com.caij.lib.utils.VolleyManager;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Caij on 2015/9/19.
 */
public class RepositoryInfoPresenterImp implements RepositoryInfoPresenter {

    @Override
    public void getRepositoryInfo(String repositoryName, String owner, String token, Object requestTag, final UiCallBack<Repository> uiCallBack) {
        uiCallBack.onLoading();
        String url = API.API_HOST + API.REPOSITORY_REPOS_URI + "/" + owner + "/" + repositoryName;
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        GsonRequest<Repository> request = new GsonRequest<Repository>(Request.Method.GET, url, "", head, new TypeToken<Repository>() {}.getType(),
                new Response.Listener<Repository>() {
                    @Override
                    public void onResponse(Repository response) {
                        uiCallBack.onSuccess(response);
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
