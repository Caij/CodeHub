package com.caij.codehub;

import android.test.AndroidTestCase;

import com.caij.codehub.bean.Token;
import com.caij.codehub.network.api.Login;
import com.caij.codehub.utils.Base64;
import com.caij.codehub.utils.OkHttpClientProvider;
import com.caij.util.LogUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2015/8/24.
 */
public class DataTest extends AndroidTestCase{

    private static final String TAG = "DataTest";

    private final static String NOTE = "note";
    private final static String SCOPES = "scopes";

    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public void testLogin() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.API_HOST)
                .client(OkHttpClientProvider.getOkHttpClient())
                .addConverterFactory(gsonConverterFactory)
//                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();

        Login service = retrofit.create(Login.class);
//         head.put(API.AUTHORIZATION, "Basic " + Base64.encode(username + ":" + pwd));
        JSONObject json = new JSONObject();
        try {
            json.put(NOTE, API.TOKEN_NOTE);
            JSONArray jsonArray = new JSONArray(Arrays.asList(API.SCOPES));
            json.put(SCOPES, jsonArray);
//            Call<Token> observable =  service.login( "Basic " + Base64.encode( "Caij:qq271945881"), json);
//            observable.subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<Token>() {
//                        @Override
//                        public void onCompleted() {
//                            LogUtil.d(TAG, "onCompleted");
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            LogUtil.d(TAG, e.getMessage());
//                        }
//
//                        @Override
//                        public void onNext(Token token) {
//                            LogUtil.d(TAG, token.toString());
//                        }
//                    });
//            Response<Token> token  = observable.execute();
//            LogUtil.d(TAG, token.code() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
