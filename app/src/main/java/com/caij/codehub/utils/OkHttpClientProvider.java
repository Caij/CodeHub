package com.caij.codehub.utils;

import com.caij.util.LogUtil;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by Caij on 2015/10/30.
 */
public class OkHttpClientProvider {

    private static final String TAG = "OkHttpClientProvider";

    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            OkHttpClient.Builder okhttpBuild = new OkHttpClient.Builder();
            try {
                // only debug use
                Class c = Class.forName("com.facebook.stetho.okhttp3.StethoInterceptor");
                okhttpBuild.addNetworkInterceptor((Interceptor) c.newInstance());
//                mOkHttpClient.networkInterceptors().add((Interceptor) c.newInstance());
//                mOkHttpClient.networkInterceptors().add(new StethoInterceptor());
            } catch (Exception e) {
                LogUtil.e(TAG, "com.facebook.stetho.okhttp3.StethoInterceptor not found");
            }
            mOkHttpClient = okhttpBuild.build();
        }
        return mOkHttpClient;
    }

}
