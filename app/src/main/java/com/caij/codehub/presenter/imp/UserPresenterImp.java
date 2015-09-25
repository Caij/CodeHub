package com.caij.codehub.presenter.imp;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.Constant;
import com.caij.codehub.bean.User;
import com.caij.codehub.presenter.UserPresenter;
import com.caij.codehub.ui.listener.UserUi;
import com.caij.lib.utils.SPUtils;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.utils.GsonUtils;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Caij on 2015/9/18.
 */
public class UserPresenterImp implements UserPresenter{

    private UserUi mUserUi;

    @Override
    public void attachUi(UserUi ui) {
        mUserUi = ui;
    }

    @Override
    public void detachUi(UserUi ui) {
        VolleyUtil.cancelRequestByTag(this);
    }

    @Override
    public void getUserInfo(String token, String username) {
        User user = getCacheUserInfo();
        if (user != null && username != null && username.equals(user.getLogin())) {
            mUserUi.onGetUserInfoSuccess(user);
        }else {
            mUserUi.showLoading(LoadType.FIRSTLOAD);
        }
        String url = API.API_HOST + "/users/" + username;
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        GsonRequest<User> request = new GsonRequest<User>(Request.Method.GET, url, null, head, new TypeToken<User>() {}.getType(),new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {
                if (response != null) {
                    mUserUi.onGetUserInfoSuccess(response);
                    SPUtils.save(Constant.USER_INFO, GsonUtils.getGson().toJson(response));
                }else {
                    mUserUi.showError(LoadType.FIRSTLOAD, null);
                }
                mUserUi.hideLoading(LoadType.FIRSTLOAD);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUserUi.hideLoading(LoadType.FIRSTLOAD);
                mUserUi.showError(LoadType.FIRSTLOAD, error);
            }
        });
        VolleyUtil.addRequest(request, this);
    }

    private User getCacheUserInfo() {
        String userInfo = SPUtils.get(Constant.USER_INFO, "");
        if (!TextUtils.isEmpty(userInfo)) {
            User user = GsonUtils.getGson().fromJson(userInfo, User.class);
            return user;
        }
        return null;
    }
}
