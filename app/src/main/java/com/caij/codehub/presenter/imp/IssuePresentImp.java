package com.caij.codehub.presenter.imp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Issue;
import com.caij.codehub.presenter.IssuePresent;
import com.caij.codehub.ui.listener.IssueUi;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Caij on 2015/10/31.
 */
public class IssuePresentImp implements IssuePresent{

    private Object tag = new Object() ;

    private IssueUi mUi;

    public IssuePresentImp(IssueUi ui) {
        this.mUi = ui;
    }

    @Override
    public void getIssue(String repo, String issueNumber) {
        mUi.showLoading(LoadType.FIRSTLOAD);
        String url = new StringBuilder().append(API.API_HOST).append("/repos/")
                .append(repo).append("/issues/")
                .append(issueNumber).toString();
        GsonRequest<Issue> request = new GsonRequest<Issue>(Request.Method.GET, url, new TypeToken<Issue>() {
        }.getType(), new Response.Listener<Issue>() {
                    @Override
                    public void onResponse(Issue response) {
                        mUi.onGetIssueSuccess(response);
                        mUi.hideLoading(LoadType.FIRSTLOAD);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUi.hideLoading(LoadType.FIRSTLOAD);
                mUi.showError(LoadType.FIRSTLOAD, error);
            }
        });
        VolleyUtil.addRequest(request, tag);
    }

    @Override
    public void onDeath() {
        VolleyUtil.cancelRequestByTag(tag);
    }
}
