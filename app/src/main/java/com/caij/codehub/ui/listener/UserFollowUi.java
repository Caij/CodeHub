package com.caij.codehub.ui.listener;

/**
 * Created by Caij on 2015/11/1.
 */
public interface UserFollowUi extends BaseUi{
    public void onCheckFollowInfoSuccess(boolean isFollow);

    public void onFollowSuccess();

    public void onFollowError();

    public void onUnfollowSuccess();

    public void onUnfollowError();
}
