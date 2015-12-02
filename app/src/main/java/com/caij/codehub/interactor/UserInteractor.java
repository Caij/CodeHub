package com.caij.codehub.interactor;

import com.caij.codehub.bean.User;

/**
 * Created by Caij on 2015/9/18.
 */
public interface UserInteractor extends Interactor {

    public void getUserInfo(String token, String username, Object requestTag, InteractorCallBack<User> interactorCallBack);


}
