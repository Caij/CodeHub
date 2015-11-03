package com.caij.codehub;

import android.app.Application;

import com.caij.codehub.utils.OkHttpClientProvider;
import com.caij.lib.utils.SPUtils;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.volley.stack.OkHttpStack;


/**
 * Created by Caij on 2015/8/25.
 */
public class CodeHubApplication extends Application{

    private static final String SP_FILE_NAME = "profile";

    private static final int VOLLEY_DISK_MAX_SIZE = 1024 * 1024 * 5;

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyUtil.init(this, new OkHttpStack(OkHttpClientProvider.getOkHttpClient()), VOLLEY_DISK_MAX_SIZE);
        SPUtils.init(this, SP_FILE_NAME);
    }

    public static String getToken() {
        return SPUtils.get(Constant.USER_TOKEN, "");
    }

    public static String getCurrentUserName() {
        return SPUtils.get(Constant.USER_NAME, "");
    }

    public static void saveToken(String token) {
        SPUtils.save(Constant.USER_TOKEN, token);
    }

    public static void saveCurrentUserName(String name) {
        SPUtils.save(Constant.USER_NAME, name);
    }


}
