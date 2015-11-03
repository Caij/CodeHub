package com.caij.codehub.presenter.imp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Comment;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.presenter.CommentsPresent;
import com.caij.codehub.ui.listener.CommentsUi;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Caij on 2015/10/31.
 */
public class CommentsPresentImp implements CommentsPresent {

    private final CommentsUi mUi;
    private Object tag = new Object();

    public CommentsPresentImp(CommentsUi ui) {
        this.mUi = ui;
    }

    @Override
    public void getIssuesComments(final int loadType, String owner, String repo, String issueNumber) {
        mUi.onLoading(loadType);
        String url = new StringBuilder().append(API.API_HOST).append("/repos/")
                .append(owner).append("/").append(repo).append("/issues/")
                .append(issueNumber).append("/comments").toString();
        GsonRequest<List<Comment>> request = new GsonRequest<List<Comment>>(Request.Method.GET, url, new TypeToken<List<Comment>>() {
        }.getType(),
                new Response.Listener<List<Comment>>() {
                    @Override
                    public void onResponse(List<Comment> response) {
                       handlerResponse(loadType, response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              handlerError(loadType, error);
            }
        });
        VolleyUtil.addRequest(request, tag);
    }

    private void handlerError(int loadType, VolleyError error) {
        mUi.onLoaded(loadType);
        if (loadType == LoadType.FIRSTLOAD) {
            mUi.onFirstLoadError(error);
        }else if (loadType == LoadType.REFRESH) {
            mUi.onRefreshError(error);
        }else if (loadType == LoadType.LOADMOER) {
            mUi.onLoadMoreError(error);
        }
    }

    private void handlerResponse(int loadType, List<Comment> comments) {
        mUi.onLoaded(loadType);
        if (loadType == LoadType.FIRSTLOAD) {
            mUi.onFirstLoadSuccess(comments);
        }else if (loadType == LoadType.REFRESH){
            mUi.onRefreshSuccess(comments);
        }  else if (loadType == LoadType.LOADMOER) {
            mUi.onLoadMoreSuccess(comments);
        }
    }

    @Override
    public void onDeath() {
        VolleyUtil.cancelRequestByTag(tag);
    }
}
