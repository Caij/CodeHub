package com.caij.codehub.presenter.imp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.User;
import com.caij.codehub.presenter.UserListPresenter;
import com.caij.codehub.ui.listener.ListUi;
import com.caij.codehub.ui.listener.UserListUi;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Caij on 2015/9/24.
 */
public class UserListPresenterImp implements UserListPresenter{

    private UserListUi mUi;
    private Object tag = new Object();

    public UserListPresenterImp(UserListUi ui) {
        mUi = ui;
    }

    @Override
    public void getFollowers(String token, String username, final int loadType, Page page) {
        showLoadingByType(mUi, loadType);
        String url = API.API_HOST + "/users/" + username + "/followers";
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);

        Map<String, String> params = new HashMap<>();
        params.put(API.PAGE, String.valueOf(page.getPageIndex()));
        params.put(API.PER_PAGE, String.valueOf(page.getPageDataCount()));

        GsonRequest<List<User>> request = new GsonRequest<List<User>>(Request.Method.GET, url, params, head, new TypeToken<List<User>>() {
        }.getType(),
                new Response.Listener<List<User>>() {
                    @Override
                    public void onResponse(List<User> response) {
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

    @Override
    public void getFollowing(String token, String username, final int loadType, Page page) {
        showLoadingByType(mUi, loadType);
        String url = API.API_HOST + "/users/" + username + "/following";
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);

        Map<String, String> params = new HashMap<>();
        params.put(API.PAGE, String.valueOf(page.getPageIndex()));
        params.put(API.PER_PAGE, String.valueOf(page.getPageDataCount()));

        GsonRequest<List<User>> request = new GsonRequest<List<User>>(Request.Method.GET, url, params, head, new TypeToken<List<User>>() {
        }.getType(),
                new Response.Listener<List<User>>() {
                    @Override
                    public void onResponse(List<User> response) {
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
        hintLoadingByType(mUi, loadType);
        onErrorByType(mUi, loadType, error);
    }

    private void handlerResponse(int loadType, List<User> users) {
        hintLoadingByType(mUi, loadType);
        onResponse(loadType, users);
    }

    private void onResponse(int loadType, List<User> users) {
        if (loadType == LoadType.REFRESH ) {
            mUi.onRefreshSuccess(users);
        } else if (loadType == LoadType.FIRSTLOAD) {
            mUi.onFirstLoadSuccess(users);
        }
        else if (loadType == LoadType.LOADMOER) {
            mUi.onLoadMoreSuccess(users);
        }
    }

    private void showLoadingByType(UserListUi ui, int loadType) {
        ui.onLoading(loadType);
    }

    private void hintLoadingByType(UserListUi ui, int loadType) {
        ui.onLoaded(loadType);
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

    @Override
    public void onDeath() {
        VolleyUtil.cancelRequestByTag(tag);
    }
}
