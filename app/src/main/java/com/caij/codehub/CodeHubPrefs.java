package com.caij.codehub;

import android.text.TextUtils;

import com.caij.codehub.bean.Token;
import com.caij.codehub.bean.User;
import com.caij.codehub.utils.Base64;
import com.caij.lib.utils.GsonUtils;
import com.caij.lib.utils.SPUtils;

/**
 * Created by Caij on 2015/11/7.
 */
public class CodeHubPrefs {

    public static final String USER_TOKEN = "user_token";
    public static final String USER_NAME = "user_name";
    public static final String USER_TOKEN_ID = "user_token_id";
    public static final String BASE64_USERNAME_AND_PWD = "base64_username_and_pwd";
    public static final String USER = "user";

    private static CodeHubPrefs singleton;

    private String username;
    private String base64UsernameAndPwd;
    private String token;
    private String tokenId;
    private User user;

    public CodeHubPrefs() {
        username = SPUtils.getString(USER_NAME, null);
        base64UsernameAndPwd = SPUtils.getString(BASE64_USERNAME_AND_PWD, null);
        token = SPUtils.getString(USER_TOKEN, null);
        tokenId = SPUtils.getString(USER_TOKEN_ID, null);
        String userString = SPUtils.getString(USER, null);
        if (!TextUtils.isEmpty(userString)) {
            user = GsonUtils.getGson().fromJson(userString, User.class);
        }
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
        this.token = token.getToken();
        this.tokenId = String.valueOf(token.getId());
        SPUtils.saveString(USER_TOKEN, token.getToken());
        SPUtils.saveString(USER_TOKEN_ID, String.valueOf(token.getId()));
    }

    public void setUser(User user) {
        if (this.user == null || !this.user.equals(user)) {
            this.user = user;
            SPUtils.saveString(USER, GsonUtils.getGson().toJson(user));
        }
    }

    public User getUser() {
        return user;
    }

    public void setUsernameAndPwd(String username, String pwd) {
        this.username = username;
        this.base64UsernameAndPwd = Base64.encode(username + ":" + pwd);
        SPUtils.saveString(USER_NAME, username);
        SPUtils.saveString(BASE64_USERNAME_AND_PWD, base64UsernameAndPwd);
    }

    public String getUsername() {
        return username;
    }

    public String getBase64UsernameAndPwd() {
        return base64UsernameAndPwd;
    }

    public String getToken() {
        return token;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void logout() {
        username = null;
        base64UsernameAndPwd = null;
        token = null;
        tokenId = null;
        SPUtils.saveString(USER_TOKEN, null);
        SPUtils.saveString(USER_NAME, null);
        SPUtils.saveString(USER_TOKEN_ID, null);
        SPUtils.saveString(BASE64_USERNAME_AND_PWD, null);
    }
}
