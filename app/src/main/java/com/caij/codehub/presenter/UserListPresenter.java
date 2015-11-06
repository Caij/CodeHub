package com.caij.codehub.presenter;

import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.User;
import com.caij.codehub.ui.callback.UiCallBack;

import java.util.List;

/**
 * Created by Caij on 2015/9/18.
 */
public interface UserListPresenter extends Present {

    public void getFollowers(String token, String username, Page page, Object requestTag, UiCallBack<List<User>> uiCallBack);

    public void getFollowing(String token, String username, Page page, Object requestTag, UiCallBack<List<User>> uiCallBack);
}
