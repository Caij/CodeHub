package com.caij.codehub;

import android.content.Context;

import com.caij.codehub.bean.Token;
import com.caij.codehub.bean.User;
import com.caij.lib.utils.SPUtils;

/**
 * Created by Caij on 2015/11/7.
 */
public class CodeHubPrefs {

    public static final String USER_TOKEN = "user_token";
    public static final String USER_NAME = "user_name";
    public static final String USER_TOKEN_ID = "user_token_id";
    public static final String USER_PWD = "user_pwd";

    private static CodeHubPrefs singleton;

    private String username;
    private String pwd;
    private String token;
    private String tokenId;

    public CodeHubPrefs() {
        username = SPUtils.getString(USER_NAME, null);
        pwd = SPUtils.getString(USER_PWD, null);
        token = SPUtils.getString(USER_TOKEN, null);
        tokenId = SPUtils.getString(USER_TOKEN_ID, null);
    }

    public static CodeHubPrefs get() {
        if (singleton == null) {
            synchronized (CodeHubPrefs.class) {
                if (singleton == null) {
                    singleton = new CodeHubPrefs();
                }
            }
        }
        return singleton;
    }

    public void setToken(Token token) {
        setToken(token.getToken());
        setTokenId(String.valueOf(token.getId()));
    }

    public void setUser(User user) {
        setUsername(user.getLogin());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        SPUtils.saveString(USER_NAME, username);
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        SPUtils.saveString(USER_PWD, pwd);
        this.pwd = pwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        SPUtils.saveString(USER_TOKEN, token);
        this.token = token;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        SPUtils.saveString(USER_TOKEN_ID, tokenId);
        this.tokenId = tokenId;
    }

    public void logout() {
        SPUtils.saveString(USER_TOKEN, null);
        SPUtils.saveString(USER_NAME, null);
        SPUtils.saveString(USER_TOKEN_ID, null);
        SPUtils.saveString(USER_PWD, null);
    }
}
