package com.caij.lib.volley.request;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.caij.lib.utils.LogUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Caij on 2015/8/25.
 */
public abstract class AbsRequest<T> extends Request<T>{

    private static final String TAG = "AbsRequest";
    protected final Map<String, String> mHead;
    protected final String mBodyContentType;
    protected final String mParams;

    public static final String JSON_BODY_CONTENT_TYPE = "application/json";
    public static final String FORM_BODY_CONTENT_TYPE = "application/x-www-form-urlencoded";

    public AbsRequest(int method, String url, Response.ErrorListener listener) {
        this(method, url, Collections.<String, String>emptyMap(), listener);
    }

    public AbsRequest(int method, String url, Map<String, String> params, Response.ErrorListener listener) {
        this(method, url, params, Collections.<String, String>emptyMap(), listener);
    }

    public AbsRequest(int method, String url, String params, Response.ErrorListener listener) {
        this(method, url, params, Collections.<String, String>emptyMap(), listener);
    }

    public AbsRequest(int method, String url, Map<String, String> params, Map<String, String> head, Response.ErrorListener listener) {
        this(method, url, params, head, FORM_BODY_CONTENT_TYPE, listener);
    }

    public AbsRequest(int method, String url, String params, Map<String, String> head, Response.ErrorListener listener) {
        this(method, url, params, head, FORM_BODY_CONTENT_TYPE, listener);
    }

    public AbsRequest(int method, String url, String params, Map<String, String> head, String bodyContentType,  Response.ErrorListener listener) {
        super(method, url, listener);
        mHead = head;
        if (TextUtils.isEmpty(bodyContentType)) {
            mBodyContentType = FORM_BODY_CONTENT_TYPE;
        }else {
            mBodyContentType = bodyContentType;
        }
        mParams = params;

        logRequest();
    }

    public AbsRequest(int method, String url, Map<String, String> params, Map<String, String> head, String bodyContentType,  Response.ErrorListener listener) {
        super(method, url, listener);
        mHead = head;
        if (TextUtils.isEmpty(bodyContentType)) {
            mBodyContentType = FORM_BODY_CONTENT_TYPE;
        }else {
            mBodyContentType = bodyContentType;
        }
        mParams = parameters2String(params, getParamsEncoding());

        logRequest();
    }

    private void logRequest() {
        if (mParams != null){
            LogUtil.d(TAG, super.getUrl() +  "?" + mParams);
        }else {
            LogUtil.d(TAG, super.getUrl());
        }
    }

    @Override
    public String getUrl() {
        if (getMethod() == Method.GET && mParams != null) {
            return super.getUrl() + "?" + mParams;
        }else {
            return super.getUrl();
        }
    }

    @Override
    public String getCacheKey() {
        return createCacheKey() ;
    }

    protected String createCacheKey() {
        StringBuilder keyBuild = new StringBuilder();
        keyBuild.append(getMethod()).append(":").append(super.getUrl());
        if (mParams != null) {
            keyBuild.append("?").append(mParams);
        }
        return keyBuild.toString();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (mHead != null) return mHead;
        else return super.getHeaders();
    }

    @Override
    public String getBodyContentType() {
        String contentType = new StringBuilder(mBodyContentType).append("; charset=").append(getParamsEncoding()).toString();
        return contentType;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (mParams == null) return null;
        return encodeStringParameters(mParams, getParamsEncoding());
    }

    private byte[] encodeStringParameters(String jsonString, String paramsEncoding) {
        try {
            return jsonString.getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }

    protected String parameters2String(Map<String, String> params, String paramsEncoding) {
        if (params != null && params.size() > 0) {
            try {
                StringBuilder encodedParams = new StringBuilder();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                    encodedParams.append('=');
                    encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                    encodedParams.append('&');
                }
                return encodedParams.toString();
            } catch (UnsupportedEncodingException uee) {
                throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
            }
        }
        return null;
    }
}
