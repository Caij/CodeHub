package com.caij.codehub.interactor;

import com.android.volley.NetworkResponse;
import com.caij.codehub.bean.Token;

import java.util.List;

/**
 * Created by Caij on 2015/8/25.
 */
public interface AuthenticationInteractor extends Interactor {
    public void login(String username, String pwd, Object requestTag, InteractorCallBack<Token> interactorCallBack);

    public void logout(String base64UsernameAndPwd, String tokenId, Object requestTag, InteractorCallBack<NetworkResponse> interactorCallBack);

    public void getHaveTokens(final String username, final String pwd, Object requestTag, final InteractorCallBack<List<Token>> interactorCallBack);

}
