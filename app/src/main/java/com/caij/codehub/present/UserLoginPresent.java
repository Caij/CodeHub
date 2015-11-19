package com.caij.codehub.present;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.R;
import com.caij.codehub.bean.Token;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.interactor.AuthenticationInteractor;
import com.caij.codehub.present.ui.UserLoginUi;
import com.caij.codehub.interactor.UiCallBack;
import com.caij.codehub.utils.Base64;

import java.util.List;

/**
 * Created by Caij on 2015/11/16.
 */
public class UserLoginPresent extends Present<UserLoginUi>{

    private final AuthenticationInteractor authenticationInteractor;

    public UserLoginPresent(UserLoginUi ui) {
        super(ui);
        authenticationInteractor = InteractorFactory.newInteractorInstance(AuthenticationInteractor.class);
    }

    public void login(final String username, final String pwd) {
        authenticationInteractor.login(username, pwd, this, new UiCallBack<Token>() {
            @Override
            public void onSuccess(Token token) {
                mUi.showProgressBarLoading(false);
                mUi.onLoginSuccess(token);
            }

            @Override
            public void onLoading() {
                mUi.showProgressBarLoading(true);
            }

            @Override
            public void onError(VolleyError error) {
                handlerLoginError(error, username, pwd);
            }
        });
    }

    private void handlerLoginError(VolleyError error, String username, String pwd) {
        if (error instanceof ServerError) {
            NetworkResponse response = ((ServerError) error).networkResponse;
            if (response != null) {
                int statusCode = response.statusCode;
                if (statusCode == 422) {
                    removeTokenByLogin(username, pwd);
                }else {
                    mUi.showError(R.string.login_error);
                    mUi.showProgressBarLoading(false);
                }
            }
        }else if (error instanceof AuthFailureError) {
            mUi.showError(R.string.password_error);
            mUi.showProgressBarLoading(false);
        } else {
            mUi.showError(R.string.login_error);
            mUi.showProgressBarLoading(false);
        }
    }

    private void removeTokenByLogin(final String username, final String pwd) {
        authenticationInteractor.getHaveTokens(username, pwd, this, new UiCallBack<List<Token>>() {
            @Override
            public void onSuccess(List<Token> tokens) {
                for (Token token : tokens) {
                    if (token != null && API.TOKEN_NOTE.equals(token.getNote())) {
                        authenticationInteractor.logout(Base64.encode(username + ":" + pwd), String.valueOf(token.getId()), this, new UiCallBack<NetworkResponse>() {
                            @Override
                            public void onSuccess(NetworkResponse networkResponse) {
                                login(username, pwd);
                            }

                            @Override
                            public void onLoading() {
                            }

                            @Override
                            public void onError(VolleyError error) {
                                mUi.showError(R.string.login_error);
                                mUi.showProgressBarLoading(false);
                            }
                        });
                        break;
                    }
                }
            }

            @Override
            public void onLoading() {
            }

            @Override
            public void onError(VolleyError error) {
                mUi.showError(R.string.login_error);
                mUi.showProgressBarLoading(false);
            }
        });
    }

}
