package com.caij.codehub.present.ui;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/16.
 */
public interface UserAndActionUi extends UserUi{

    public void onGetFollowInfoSuccess(boolean isFollow);
    public void onGetFollowInfoError(boolean isFollow);

}
