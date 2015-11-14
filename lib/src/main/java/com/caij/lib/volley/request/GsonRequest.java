package com.caij.lib.volley.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.caij.lib.utils.GsonUtils;
import com.google.gson.JsonSyntaxException;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by Caij on 2015/8/24.
 */
public class GsonRequest<T> extends AbsRequest<T>{

    protected Type mType;
    protected Response.Listener<T> mResponse;


    public GsonRequest(int method, String url, Type type,
                       Response.Listener<T> response, Response.ErrorListener listener) {
        super(method, url, listener);
        mType = type;
        mResponse = response;
    }

    public GsonRequest(int method, String url, Map<String, String> params, Type type, Response.Listener<T> response, Response.ErrorListener listener) {
        super(method, url, params, listener);
        mType = type;
        mResponse = response;
    }

    public GsonRequest(int method, String url, String params, Type type, Response.Listener<T> response, Response.ErrorListener listener) {
        super(method, url, params, listener);
        mType = type;
        mResponse = response;
    }

    public GsonRequest(int method, String url, Map<String, String> params, Map<String, String> head, Type type,
                       Response.Listener<T> response, Response.ErrorListener listener) {
        super(method, url, params, head, listener);
        mType = type;
        mResponse = response;
    }

    public GsonRequest(int method, String url, String params, Map<String, String> head, Type type,
                       Response.Listener<T> response, Response.ErrorListener listener) {
        super(method, url, params, head, listener);
        mType = type;
        mResponse = response;
    }

    public GsonRequest(int method, String url, Map<String, String> params, Map<String, String> head, String bodyContentType, Type type,
                       Response.Listener<T> response, Response.ErrorListener listener) {
        super(method, url, params, head, bodyContentType, listener);
        mType = type;
        mResponse = response;
    }

    public GsonRequest(int method, String url, String params, Map<String, String> head, String bodyContentType, Type type,
                       Response.Listener<T> response, Response.ErrorListener listener) {
        super(method, url, params, head, bodyContentType, listener);
        mType = type;
        mResponse = response;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        T result;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(response.data);
        try {
            result = GsonUtils.getGson().fromJson(new InputStreamReader(byteArrayInputStream, HttpHeaderParser.parseCharset(response.headers)), mType);
            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        } catch (JsonSyntaxException e) {
            return Response.error(new JsonParseError());
        }catch (UnsupportedEncodingException e) {
            try {
                result = GsonUtils.getGson().fromJson(new InputStreamReader(byteArrayInputStream, Charset.forName("UTF-8")), mType);
                return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
            }catch (JsonSyntaxException je) {
                return Response.error(new JsonParseError());
            }
        }
    }

    @Override
    protected void deliverResponse(T response) {
        if (mResponse != null) {
            mResponse.onResponse(response);
        }
    }
}
