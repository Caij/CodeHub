package com.caij.lib.volley.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.caij.lib.utils.LogUtil;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Caij on 2015/9/17.
 */
public class StringRequest extends AbsRequest<String>{

    protected final Response.Listener<String> mResponse;

    public StringRequest(int method, String url, Response.Listener<String> response, Response.ErrorListener listener) {
        super(method, url, listener);
        mResponse = response;
    }

    public StringRequest(int method, String url, Map<String, String> params, Response.Listener<String> response, Response.ErrorListener listener) {
        super(method, url, params, listener);
        mResponse = response;
    }

    public StringRequest(int method, String url, String params, Response.Listener<String> response, Response.ErrorListener listener) {
        super(method, url, params, listener);
        mResponse = response;
    }

    public StringRequest(int method, String url, Map<String, String> params, Map<String, String> head, Response.Listener<String> response, Response.ErrorListener listener) {
        super(method, url, params, head, listener);
        mResponse = response;
    }

    public StringRequest(int method, String url, String params, Map<String, String> head, Response.Listener<String> response, Response.ErrorListener listener) {
        super(method, url, params, head, listener);
        mResponse = response;
    }

    public StringRequest(int method, String url, String params, Map<String, String> head, String bodyContentType, Response.Listener<String> response, Response.ErrorListener listener) {
        super(method, url, params, head, bodyContentType, listener);
        mResponse = response;
    }

    public StringRequest(int method, String url, Map<String, String> params, Map<String, String> head, String bodyContentType, Response.Listener<String> response, Response.ErrorListener listener) {
        super(method, url, params, head, bodyContentType, listener);
        mResponse = response;
    }

    @Override
    protected void deliverResponse(String response) {
        if (mResponse != null) {
            mResponse.onResponse(response);
        }
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        LogUtil.d(this.getClass().getName(), parsed);
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }
}
