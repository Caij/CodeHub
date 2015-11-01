package com.caij.codehub.presenter;

import com.caij.codehub.ui.listener.UserUi;

/**
 * Created by Caij on 2015/9/18.
 */
public interface UserPresenter extends BasePresent<UserUi>{

    public void getUserInfo(String token, String username);


}
