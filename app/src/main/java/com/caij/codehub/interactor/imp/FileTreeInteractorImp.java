package com.caij.codehub.interactor.imp;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Tree;
import com.caij.codehub.interactor.FileTreeInteractor;
import com.caij.codehub.interactor.UiCallBack;
import com.caij.lib.utils.VolleyManager;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Caij on 2015/11/2.
 */
public class FileTreeInteractorImp implements FileTreeInteractor {

    @Override
    public void loadFileTree(String name, String repo, String ref,  Object requestTag, final UiCallBack<Tree> uiCallBack) {
        uiCallBack.onLoading();
        StringBuilder builder = new StringBuilder();
        builder.append(API.API_HOST).append("/repos/").append(name).append("/").
                append(repo).append("/git/trees/");
        if (!TextUtils.isEmpty(ref)) {
            builder.append(ref);
        }
        String url = builder.toString();
                GsonRequest<Tree> request = new GsonRequest<Tree>(Request.Method.GET, url, new TypeToken<Tree>() {
        }.getType(), new Response.Listener<Tree>() {
            @Override
            public void onResponse(Tree response) {
                uiCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                uiCallBack.onError(error);
            }
        });
        VolleyManager.addRequest(request, null);
    }

}
