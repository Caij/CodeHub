package com.caij.codehub;

import android.app.Application;

import com.caij.codehub.utils.OkHttpClientProvider;
import com.caij.lib.utils.LogUtil;
import com.caij.lib.utils.SPUtils;
import com.caij.lib.utils.VolleyManager;
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
        SPUtils.init(this, SP_FILE_NAME);
        LogUtil.LOG_DEBUG = BuildConfig.LOG_DEBUG;
        VolleyManager.init(this, new OkHttpStack(OkHttpClientProvider.getOkHttpClient()), VOLLEY_DISK_MAX_SIZE);
    }

}
