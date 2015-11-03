package com.caij.codehub.presenter.imp;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.presenter.RepositoryInfoPresenter;
import com.caij.codehub.ui.listener.RepositoryInfoUi;
import com.caij.lib.utils.LogUtil;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.volley.request.GsonRequest;
import com.caij.lib.volley.request.NetworkResponseRequest;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Caij on 2015/9/19.
 */
public class RepositoryInfoPresenterImp implements RepositoryInfoPresenter {

    private RepositoryInfoUi mUi;
    private Object tag = new Object();

    public RepositoryInfoPresenterImp(RepositoryInfoUi ui) {
        this.mUi = ui;
    }

    @Override
    public void getRepositoryInfo(String repositoryName, String owner, String token) {
        mUi.onLoading();
        String url = API.API_HOST + API.REPOSITORY_REPOS_URI + "/" + owner + "/" + repositoryName;
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        GsonRequest<Repository> request = new GsonRequest<Repository>(Request.Method.GET, url, "", head, new TypeToken<Repository>() {}.getType(),
                new Response.Listener<Repository>() {
                    @Override
                    public void onResponse(Repository response) {
                        mUi.onGetRepositoryInfoSuccess(response);
                        mUi.onLoaded();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUi.onLoaded();
                mUi.onGetRepositoryInfoError(error);
            }
        });
        VolleyUtil.addRequest(request, tag);
    }

    @Override
    public void onDeath() {
        VolleyUtil.cancelRequestByTag(tag);
    }
}
