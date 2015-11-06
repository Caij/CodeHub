package com.caij.codehub.presenter;

import com.android.volley.NetworkResponse;
import com.caij.codehub.ui.callback.UiCallBack;

/**
 * Created by Caij on 2015/11/1.
 */
public interface FollowActionPresent extends Present {

    public void checkFollowState(String token, String username, Object requestTag, UiCallBack<Boolean> uiCallBack);

    public void followUser(String token, String username, Object requestTag, UiCallBack<NetworkResponse> uiCallBack);

    public void unfollowUser(String token, String username, Object requestTag, UiCallBack<NetworkResponse> uiCallBack);

}
