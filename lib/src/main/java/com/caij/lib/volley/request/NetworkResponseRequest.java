package com.caij.lib.volley.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.caij.util.LogUtil;

import java.util.Map;

/**
 * Created by Caij on 2015/10/31.
 */
public class NetworkResponseRequest extends AbsRequest<NetworkResponse>{

    private static final String TAG = "NetworkResponseRequest";

    protected Response.Listener<NetworkResponse> mResponse;

    public NetworkResponseRequest(int method, String url, Response.Listener<NetworkResponse> response, Response.ErrorListener listener) {
        super(method, url, listener);
        mResponse = response;
    }

    public NetworkResponseRequest(int method, String url, Map<String, String> params, Response.Listener<NetworkResponse> response, Response.ErrorListener listener) {
        super(method, url, params, listener);
        mResponse = response;
    }

    public NetworkResponseRequest(int method, String url, String params, Response.Listener<NetworkResponse> response, Response.ErrorListener listener) {
        super(method, url, params, listener);
        mResponse = response;
    }

    public NetworkResponseRequest(int method, String url, Map<String, String> params, Map<String, String> head, Response.Listener<NetworkResponse> response, Response.ErrorListener listener) {
        super(method, url, params, head, listener);
        mResponse = response;
    }

    public NetworkResponseRequest(int method, String url, String params, Map<String, String> head, Response.Listener<NetworkResponse> response, Response.ErrorListener listener) {
        super(method, url, params, head, listener);
        mResponse = response;
    }

    public NetworkResponseRequest(int method, String url, String params, Map<String, String> head, String bodyContentType, Response.Listener<NetworkResponse> response, Response.ErrorListener listener) {
        super(method, url, params, head, bodyContentType, listener);
        mResponse = response;
    }

    public NetworkResponseRequest(int method, String url, Map<String, String> params, Map<String, String> head, String bodyContentType, Response.Listener<NetworkResponse> response, Response.ErrorListener listener) {
        super(method, url, params, head, bodyContentType, listener);
        mResponse = response;
    }

    @Override
    protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
        LogUtil.d(TAG, String.valueOf(response.statusCode));
        return Response.success(response, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(NetworkResponse response) {
        mResponse.onResponse(response);
    }
}
