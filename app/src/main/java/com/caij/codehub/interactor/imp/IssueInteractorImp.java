package com.caij.codehub.interactor.imp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Issue;
import com.caij.codehub.interactor.IssueInteractor;
import com.caij.codehub.interactor.UiCallBack;
import com.caij.lib.utils.VolleyManager;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Caij on 2015/10/31.
 */
public class IssueInteractorImp implements IssueInteractor {

    @Override
    public void getIssue(String owner, String repo, String issueNumber, Object requestTag, final UiCallBack<Issue> uiCallBack) {
        uiCallBack.onLoading();
        String url = new StringBuilder().append(API.API_HOST).append("/repos/").append(owner)
                .append("/").append(repo).append("/issues/")
                .append(issueNumber).toString();
        GsonRequest<Issue> request = new GsonRequest<Issue>(Request.Method.GET, url, new TypeToken<Issue>() {
        }.getType(), new Response.Listener<Issue>() {
                    @Override
                    public void onResponse(Issue response) {
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
