package com.caij.lib.volley.request;

import android.nfc.Tag;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.caij.lib.utils.GsonUtils;
import com.caij.lib.utils.LogUtil;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by Caij on 2015/8/24.
 */
public class GsonRequest<T> extends AbsRequest<T>{

    private final static String TAG = "GsonRequest";

    protected Type mType;
    protected Response.Listener<T> mResponse;

    public GsonRequest(int method, String url, Type type,
                       Response.Listener<T> response, Response.ErrorListener listener) {
        super(method, url, listener);
        mType = type;
        mResponse = response;
    }

    public GsonRequest(int method, String url, Map<String, String> params, Type type,
                       Response.Listener<T> response, Response.ErrorListener listener) {
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

    public GsonRequest(int method, String url, Map<String, String> params, Map<String, String> head, String bodyContentType, Type type,
                       Response.Listener<T> response, Response.ErrorListener listener) {
        super(method, url, params, head, bodyContentType, listener);
        mType = type;
        mResponse = response;
    }

    public GsonRequest(int method, String url, String body, Map<String, String> head, String bodyContentType, Type type,
                       Response.Listener<T> response, Response.ErrorListener listener) {
        super(method, url, body, head, bodyContentType, listener);
        mType = type;
        mResponse = response;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        T result;
        try {
            String parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            LogUtil.json(TAG, parsed);
            result = GsonUtils.getGson().fromJson(parsed, mType);
            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        } catch (JsonSyntaxException e) {
            return Response.error(new JsonParseError());
        }catch (UnsupportedEncodingException e) {
            try {
                String parsed = new String(response.data, Charset.forName("UTF-8"));
                result = GsonUtils.getGson().fromJson(parsed, mType);
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
