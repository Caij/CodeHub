package com.caij.codehub.network.api;

import com.caij.codehub.bean.AuthorizationParameters;
import com.caij.codehub.bean.Token;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Caij on 2016/3/30.
 */
public interface Login {

    @POST("/authorizations")
    Observable<Token> login(@Header("Authorization")String authorization, @Body AuthorizationParameters authorizationParameters);

    @DELETE("/authorizations/{id}")
    Observable<Object> logout(@Header("Authorization")String authorization, @Path("id") String id);

    @GET("/authorizations")
    Observable<List<Token>> getHadTokens(@Header("Authorization")String authorization);
}
