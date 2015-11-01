package com.caij.codehub.presenter;

import com.caij.codehub.ui.listener.UserFollowUi;

/**
 * Created by Caij on 2015/11/1.
 */
public interface UserFollowPresent extends BasePresent<UserFollowUi>{

    public void checkFollowState(String token, String username);

    public void followUser(String token, String username);

    public void unfollowUser(String token, String username);

}
