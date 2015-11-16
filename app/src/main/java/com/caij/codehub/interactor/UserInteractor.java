package com.caij.codehub.interactor;

import com.caij.codehub.bean.User;
import com.caij.codehub.ui.callback.UiCallBack;

/**
 * Created by Caij on 2015/9/18.
 */
public interface UserInteractor extends Interactor {

    public void getUserInfo(String token, String username, Object requestTag, UiCallBack<User> uiCallBack);


}
