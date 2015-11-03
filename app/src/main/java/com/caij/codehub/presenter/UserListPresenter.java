package com.caij.codehub.presenter;

import com.caij.codehub.bean.Page;
import com.caij.codehub.ui.listener.UserListUi;

/**
 * Created by Caij on 2015/9/18.
 */
public interface UserListPresenter extends Present<UserListUi> {

    public void getFollowers(String token, String username, int loadType, Page page);

    public void getFollowing(String token, String username, int loadType, Page page);
}
