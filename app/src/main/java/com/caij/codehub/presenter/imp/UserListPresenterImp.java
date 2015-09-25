package com.caij.codehub.presenter.imp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.Constant;
import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.User;
import com.caij.codehub.presenter.UserListPresenter;
import com.caij.codehub.ui.listener.UserListUi;
import com.caij.codehub.ui.listener.UserUi;
import com.caij.lib.utils.GsonUtils;
import com.caij.lib.utils.SPUtils;
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

    @Override
    public void attachUi(UserListUi ui) {
        mUi = ui;
    }

    @Override
    public void detachUi(UserListUi ui) {
        VolleyUtil.cancelRequestByTag(this);
    }

    @Override
    public void getFollowers(String token, String username, final int loadType, Page page) {
        mUi.showLoading(loadType);
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
        VolleyUtil.addRequest(request, this);
    }

    @Override
    public void getFollowing(String token, String username, final int loadType, Page page) {
        mUi.showLoading(loadType);
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
        VolleyUtil.addRequest(request, this);
    }

    private void handlerError(int loadType, VolleyError error) {
        mUi.hideLoading(loadType);
        mUi.showError(loadType, error);
    }

    private void handlerResponse(int loadType, List<User> users) {
        mUi.hideLoading(loadType);
        if (users != null) {
            if (loadType == LoadType.REFRESH || loadType == LoadType.FIRSTLOAD) {
                mUi.onGetUsersSuccess(users);
            } else if (loadType == LoadType.LOADMOER) {
                mUi.onLoadMoreSuccess(users);
            }
        }else {
            mUi.showError(loadType, null);
        }
    }
}
