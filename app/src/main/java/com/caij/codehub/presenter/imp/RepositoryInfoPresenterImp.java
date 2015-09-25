package com.caij.codehub.presenter.imp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.presenter.RepositoryInfoPresenter;
import com.caij.codehub.ui.listener.RepositoryInfoUi;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Caij on 2015/9/19.
 */
public class RepositoryInfoPresenterImp implements RepositoryInfoPresenter {

    private RepositoryInfoUi mUi;

    @Override
    public void getRepositoryInfo(String repositoryName, String owner, String token) {
        mUi.showLoading(LoadType.FIRSTLOAD);
        String url = API.API_HOST + API.REPOSITORY_REPOS_URI + "/" + owner + "/" + repositoryName;
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        GsonRequest<Repository> request = new GsonRequest<Repository>(Request.Method.GET, url, null, head, new TypeToken<Repository>() {}.getType(),
                new Response.Listener<Repository>() {
                    @Override
                    public void onResponse(Repository response) {
                        if (response != null) {
                            mUi.onGetRepositoryInfoSuccess(response);
                        }else {
                            mUi.showError(LoadType.FIRSTLOAD, null);
                        }
                        mUi.hideLoading(LoadType.FIRSTLOAD);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUi.hideLoading(LoadType.FIRSTLOAD);
                mUi.showError(LoadType.FIRSTLOAD, error);
            }
        });
        VolleyUtil.addRequest(request, this);
    }

    @Override
    public void attachUi(RepositoryInfoUi ui) {
        mUi = ui;
    }

    @Override
    public void detachUi(RepositoryInfoUi ui) {
        VolleyUtil.cancelRequestByTag(this);
    }
}
