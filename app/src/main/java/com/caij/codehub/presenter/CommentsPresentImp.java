package com.caij.codehub.presenter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Comment;
import com.caij.codehub.ui.listener.CommentsUi;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Caij on 2015/10/31.
 */
public class CommentsPresentImp implements CommentsPresent{

    private final CommentsUi mUi;
    private Object tag = new Object();

    public CommentsPresentImp(CommentsUi ui) {
        this.mUi = ui;
    }

    @Override
    public void getIssuesComments(String repo, String issueNumber) {
        mUi.showLoading(LoadType.FIRSTLOAD);
        String url = new StringBuilder().append(API.API_HOST).append("/repos/")
                .append(repo).append("/issues/")
                .append(issueNumber).append("/comments").toString();
        GsonRequest<List<Comment>> request = new GsonRequest<List<Comment>>(Request.Method.GET, url, new TypeToken<List<Comment>>() {
        }.getType(),
                new Response.Listener<List<Comment>>() {
                    @Override
                    public void onResponse(List<Comment> response) {
                        mUi.onGetCommentsSuccess(response);
                        mUi.hideLoading(LoadType.FIRSTLOAD);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUi.showError(LoadType.FIRSTLOAD, error);
                mUi.hideLoading(LoadType.FIRSTLOAD);
            }
        });
        VolleyUtil.addRequest(request, tag);
    }

    @Override
    public void onDeath() {
        VolleyUtil.cancelRequestByTag(tag);
    }
}
