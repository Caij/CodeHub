package com.caij.codehub.presenter;

import com.caij.codehub.ui.listener.UserFollowUi;

/**
 * Created by Caij on 2015/11/1.
 */
public interface FollowActionPresent extends Present<UserFollowUi> {

    public static final int ACTION_TYPE_HAS_FOLLOW = 1;
    public static final int ACTION_TYPE_FOLLOW= 2;
    public static final int ACTION_TYPE_UNFOLLOW = 3;

    public void checkFollowState(String token, String username);

    public void followUser(String token, String username);

    public void unfollowUser(String token, String username);

}
