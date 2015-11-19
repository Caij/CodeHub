package com.caij.codehub.interactor.imp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Comment;
import com.caij.codehub.interactor.CommentsInteractor;
import com.caij.codehub.interactor.UiCallBack;
import com.caij.lib.utils.VolleyManager;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Caij on 2015/10/31.
 */
public class CommentsInteractorImp implements CommentsInteractor {


    @Override
    public void getIssuesComments(String owner, String repo, String issueNumber, Object requestTag, final UiCallBack<List<Comment>> uiCallBack) {
        uiCallBack.onLoading();
        String url = new StringBuilder().append(API.API_HOST).append("/repos/")
                .append(owner).append("/").append(repo).append("/issues/")
                .append(issueNumber).append("/comments").toString();
        GsonRequest<List<Comment>> request = new GsonRequest<List<Comment>>(Request.Method.GET, url, new TypeToken<List<Comment>>() {
        }.getType(),
                new Response.Listener<List<Comment>>() {
                    @Override
                    public void onResponse(List<Comment> response) {
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
