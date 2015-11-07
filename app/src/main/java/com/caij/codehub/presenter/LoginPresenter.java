package com.caij.codehub.presenter;

import com.android.volley.NetworkResponse;
import com.caij.codehub.bean.Token;
import com.caij.codehub.ui.callback.UiCallBack;

/**
 * Created by Caij on 2015/8/25.
 */
public interface LoginPresenter extends Present {
    public void login(String username, String pwd, Object requestTag, UiCallBack<Token> uiCallBack);

    public void logout(String username, String pwd, String tokenId, Object requestTag, UiCallBack<NetworkResponse> uiCallBack);
}
