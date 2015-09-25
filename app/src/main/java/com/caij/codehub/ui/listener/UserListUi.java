package com.caij.codehub.ui.listener;

import com.caij.codehub.bean.User;

import java.util.List;

/**
 * Created by Caij on 2015/9/18.
 */
public interface UserListUi extends BaseUi{

    public void onGetUsersSuccess(List<User> users);

    public void onLoadMoreSuccess(List<User> users);

}
