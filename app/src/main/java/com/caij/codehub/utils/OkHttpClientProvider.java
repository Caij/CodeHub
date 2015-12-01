package com.caij.codehub.utils;

import com.caij.lib.utils.LogUtil;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

/**
 * Created by Caij on 2015/10/30.
 */
public class OkHttpClientProvider {

    private static final String TAG = "OkHttpClientProvider";

    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
            try {
                // only debug use
                Class c = Class.forName("com.facebook.stetho.okhttp.StethoInterceptor");
                mOkHttpClient.networkInterceptors().add((Interceptor) c.newInstance());
            } catch (Exception e) {
                LogUtil.e(TAG, "com.facebook.stetho.okhttp.StethoInterceptor not found");
            }
        }
        return mOkHttpClient;
    }

}
