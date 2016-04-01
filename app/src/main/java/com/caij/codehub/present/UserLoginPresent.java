package com.caij.codehub.present;

import com.caij.codehub.API;
import com.caij.codehub.bean.AuthorizationParameters;
import com.caij.codehub.bean.Token;
import com.caij.codehub.network.NetWork;
import com.caij.codehub.network.api.Login;
import com.caij.codehub.present.ui.UserLoginUi;
import com.caij.codehub.utils.Base64;
import com.caij.util.LogUtil;

import java.util.Arrays;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Caij on 2015/11/16.
 */
public class UserLoginPresent {

    private final Login loginApi;

    public UserLoginPresent() {
        loginApi = NetWork.getLoginApi();
    }

    public Observable<Token> login(final String username, final String pwd) {
        final String authorization = "Basic " + Base64.encode(username +  ":" + pwd);
        final AuthorizationParameters parameters = new AuthorizationParameters();
        parameters.setScopes(Arrays.asList(API.SCOPES));
        parameters.setNote(API.TOKEN_NOTE);
        return loginApi.login(authorization , parameters).
        flatMap(new Func1<Token, Observable<Token>>() {
            @Override
            public Observable<Token> call(Token token) {
                return Observable.just(token);
            }
        }, new Func1<Throwable, Observable<Token>>() {
            @Override
            public Observable<Token> call(Throwable throwable) {
                if (throwable instanceof HttpException) {
                    HttpException exception = (HttpException) throwable;
                    if (exception.response().code() == 422) {
                        return getHasToken(authorization, parameters);
                    }
                }
                throw new RuntimeException(throwable);
            }
        }, new Func0<Observable<Token>>() {
            @Override
            public Observable<Token> call() {
                return Observable.never();
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<Token> getHasToken(final String authorization, final AuthorizationParameters parameters){
        return loginApi.getHadTokens(authorization)
                .flatMap(new Func1<List<Token>, Observable<Token>>() {
                    @Override
                    public Observable<Token> call(List<Token> tokens) {
                        return Observable.from(tokens);
                    }
                })
                .first(new Func1<Token, Boolean>() {
                    @Override
                    public Boolean call(Token token) {
                        return API.TOKEN_NOTE.equals(token.getNote());
                    }
                })
                .flatMap(new Func1<Token, Observable<Object>>() {
                    @Override
                    public Observable<Object> call(Token token) {
                        return loginApi.logout(authorization, String.valueOf(token.getId()));
                    }
                })
                .flatMap(new Func1<Object, Observable<Token>>() {
                    @Override
                    public Observable<Token> call(Object response) {
                        return loginApi.login(authorization, parameters);
                    }
                });
    }

}
