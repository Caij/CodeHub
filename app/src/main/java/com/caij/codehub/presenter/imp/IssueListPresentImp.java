package com.caij.codehub.presenter.imp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Issue;
import com.caij.codehub.bean.Page;
import com.caij.codehub.presenter.IssueListPresent;
import com.caij.codehub.ui.listener.IssueListUi;
import com.caij.codehub.ui.listener.IssueUi;
import com.caij.codehub.ui.listener.ListUi;
import com.caij.codehub.ui.listener.UserListUi;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Caij on 2015/11/3.
 */
public class IssueListPresentImp implements IssueListPresent {

    private IssueListUi mUi;

    public IssueListPresentImp(IssueListUi ui) {
        mUi = ui;
    }

    @Override
    public void getIssueList(final int loadType, String owner, String repoName, Page page) {
        showLoadingByType(mUi, loadType);
        StringBuilder builder = new StringBuilder(API.API_HOST);
        builder.append("/repos/").append(owner).append("/").append(repoName).append("/").append("issues");
        String url = builder.toString();

        Map<String, String> params = new HashMap<>();
        params.put(API.PAGE, String.valueOf(page.getPageIndex()));
        params.put(API.PER_PAGE, String.valueOf(page.getPageDataCount()));

        GsonRequest<List<Issue>> request = new GsonRequest<List<Issue>>(Request.Method.GET, url, params, new TypeToken<List<Issue>>() {
        }.getType(),
                new Response.Listener<List<Issue>>() {
                    @Override
                    public void onResponse(List<Issue> response) {
                        handlerResponse(loadType, response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handlerError(loadType, error);
            }
        });
        VolleyUtil.addRequest(request, null);
    }

    private void handlerError(int loadType, VolleyError error) {
        hintLoadingByType(mUi, loadType);
        onErrorByType(mUi, loadType, error);
    }

    private void handlerResponse(int loadType, List<Issue> users) {
        hintLoadingByType(mUi, loadType);
        onResponse(loadType, users);
    }

    private void onResponse(int loadType, List<Issue> users) {
        if (loadType == LoadType.REFRESH ) {
            mUi.onRefreshSuccess(users);
        } else if (loadType == LoadType.FIRSTLOAD) {
            mUi.onFirstLoadSuccess(users);
        }
        else if (loadType == LoadType.LOADMOER) {
            mUi.onLoadMoreSuccess(users);
        }
    }

    private void onErrorByType(ListUi ui, int loadType, VolleyError error) {
        if (loadType == LoadType.FIRSTLOAD) {
            ui.onFirstLoadError(error);
        }else if (loadType == LoadType.REFRESH){
            ui.onRefreshError(error);
        }else if (loadType == LoadType.LOADMOER){
            ui.onLoadMoreError(error);
        }
    }
    private void showLoadingByType(ListUi ui, int loadType) {
        ui.onLoading(loadType);
    }

    private void hintLoadingByType(ListUi ui, int loadType) {
        ui.onLoaded(loadType);
    }

    @Override
    public void onDeath() {

    }
}
