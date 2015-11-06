package com.caij.codehub.presenter;

import com.caij.codehub.bean.User;
import com.caij.codehub.ui.callback.UiCallBack;

/**
 * Created by Caij on 2015/9/18.
 */
public interface UserPresenter extends Present {

    public void getUserInfo(String token, String username, Object requestTag, UiCallBack<User> uiCallBack);


}
