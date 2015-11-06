package com.caij.codehub;

import android.app.Application;
import android.text.TextUtils;

import com.caij.codehub.utils.OkHttpClientProvider;
import com.caij.lib.utils.AppManager;
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

    public static String getCurrentUserName() {
        return SPUtils.getString(Constant.USER_NAME, "");
    }

    public static void saveCurrentUserName(String name) {
        SPUtils.saveString(Constant.USER_NAME, name);
    }


}
