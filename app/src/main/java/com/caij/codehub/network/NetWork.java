package com.caij.codehub.network;

import com.caij.codehub.API;
import com.caij.codehub.network.api.Login;
import com.caij.codehub.utils.OkHttpClientProvider;

import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Caij on 2016/4/1.
 */
public class NetWork {


    private static final Retrofit sRetrofit;

    static {
        Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
        CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
        sRetrofit = new Retrofit.Builder()
                .baseUrl(API.API_HOST)
                .client(OkHttpClientProvider.getOkHttpClient())
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    public static Login getLoginApi() {
        return sRetrofit.create(Login.class);
    }
}
