package com.caij.codehub.interactor;

import com.android.volley.NetworkResponse;

/**
 * Created by Caij on 2015/11/1.
 */
public interface FollowActionInteractor extends Interactor {

    public void getFollowState(String token, String username, Object requestTag, InteractorCallBack<Boolean> interactorCallBack);

    public void followUser(String token, String username, Object requestTag, InteractorCallBack<NetworkResponse> interactorCallBack);

    public void unfollowUser(String token, String username, Object requestTag, InteractorCallBack<NetworkResponse> interactorCallBack);

}
