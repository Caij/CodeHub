package com.caij.codehub.interactor;

import com.android.volley.NetworkResponse;
import com.caij.codehub.ui.callback.UiCallBack;

/**
 * Created by Caij on 2015/11/1.
 */
public interface FollowActionInteractor extends Interactor {

    public void getFollowState(String token, String username, Object requestTag, UiCallBack<Boolean> uiCallBack);

    public void followUser(String token, String username, Object requestTag, UiCallBack<NetworkResponse> uiCallBack);

    public void unfollowUser(String token, String username, Object requestTag, UiCallBack<NetworkResponse> uiCallBack);

}
