package com.caij.codehub.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import java.io.InputStream;


/**
 * Created by zhongweiguang on 15/8/14.
 */
public class MyGlideModule implements GlideModule {

    private static final int DISK_CACHE_SIZE = 365 * 1024 * 1024; // 365MB
    public static final String DISK_CACHE_NAME = "glide_disk_cache";

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(OkHttpClientProvider.getOkHttpClient()));
    }

}
