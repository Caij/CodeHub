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

    public interface ContentType {
        public static final String JSON_BODY_CONTENT_TYPE = "application/json";
        public static final String FORM_BODY_CONTENT_TYPE = "application/x-www-form-urlencoded";
    }

    private static final String TAG = "AbsRequest";
    protected Map<String, String> mHead;
    protected String mBodyContentType;
    protected String mParams;
    protected String mCacheKey;

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
        this(method, url, params, head, ContentType.FORM_BODY_CONTENT_TYPE, listener);
    }

    public AbsRequest(int method, String url, String params, Map<String, String> head, Response.ErrorListener listener) {
        this(method, url, params, head, ContentType.FORM_BODY_CONTENT_TYPE, listener);
    }

    public AbsRequest(int method, String url, String params, Map<String, String> head, String bodyContentType,  Response.ErrorListener listener) {
        super(method, url, listener);
        init(params, head, bodyContentType);
    }

    public AbsRequest(int method, String url, Map<String, String> params, Map<String, String> head, String bodyContentType,  Response.ErrorListener listener) {
        super(method, url, listener);
        init(parameters2String(params, getParamsEncoding()), head, bodyContentType);
    }

    private void init(String params, Map<String, String> head, String bodyContentType) {
        if (TextUtils.isEmpty(bodyContentType)) {
            mBodyContentType = ContentType.FORM_BODY_CONTENT_TYPE;
        }else {
            mBodyContentType = bodyContentType;
        }

        if (head == null) {
            mHead = Collections.<String, String>emptyMap();
        }else {
            mHead = head;
        }

        mParams = params;

        mCacheKey = createCacheKey();

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
        return mCacheKey ;
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
        else return Collections.<String, String>emptyMap();
    }

    @Override
    public String getBodyContentType() {
        return new StringBuilder(mBodyContentType).append("; charset=").append(getParamsEncoding()).toString();
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

    protected static String parameters2String(Map<String, String> params, String paramsEncoding) {
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
