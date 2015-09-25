package com.caij.lib.volley.request;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.caij.lib.utils.LogUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Caij on 2015/8/25.
 */
public abstract class AbsRequest<T> extends Request<T>{

    private static final String TAG = "AbsRequest";
    protected final Map<String, String> mParams;
    protected final Map<String, String> mHead;
    protected final String mBodyContentType;
    protected final String mBody;

    public static final String JSON_BODY_CONTENT_TYPE = "application/json; charset=";
    public static final String FORM_BODY_CONTENT_TYPE = "application/x-www-form-urlencoded; charset=";

    public AbsRequest(int method, String url, Response.ErrorListener listener) {
        this(method, url, null, listener);
    }

    public AbsRequest(int method, String url, Map<String, String> params, Response.ErrorListener listener) {
        this(method, url, params, null, listener);
    }

    public AbsRequest(int method, String url, Map<String, String> params, Map<String, String> head, Response.ErrorListener listener) {
        this(method, url, params, head, null, listener);
    }

    public AbsRequest(int method, String url, Map<String, String> params, Map<String, String> head, String bodyContentType, Response.ErrorListener listener) {
        this(method, url, params, null, head, bodyContentType, listener);
    }

    public AbsRequest(int method, String url, String body, Map<String, String> head, String bodyContentType, Response.ErrorListener listener) {
        this(method, url, null, body, head, bodyContentType, listener);
    }

    private AbsRequest(int method, String url, Map<String, String> params, String body, Map<String, String> head, String bodyContentType,  Response.ErrorListener listener) {
        super(method, url, listener);
        mHead = head;

        if (TextUtils.isEmpty(bodyContentType)) {
            mBodyContentType = FORM_BODY_CONTENT_TYPE;
        }else {
            mBodyContentType = bodyContentType;
        }

        if (params != null && !TextUtils.isEmpty(body)) {
            throw new IllegalArgumentException("params and body can't exist at the same time");
        }

        mParams = params;
        mBody = body;
    }

    @Override
    public String getUrl() {
        if (getMethod() == Method.GET && mParams != null && mParams.size() > 0) {
            String url = super.getUrl() + "?" + parameters2String(mParams, getParamsEncoding());
            LogUtil.d(TAG, url);
            return url;
        }
        return super.getUrl();
    }

    @Override
    public String getCacheKey() {
        return createCacheKey() ;
    }

    protected String createCacheKey() {
        if (mParams != null && mParams.size() > 0) {
            String params = parameters2String(mParams, getParamsEncoding());
            return getMethod() + ":" + super.getUrl() + "?" + params;
        }
        return getMethod() + ":" + super.getUrl();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (mHead!= null) return mHead;
        else return super.getHeaders();
    }

    @Override
    public String getBodyContentType() {
        return mBodyContentType + getParamsEncoding();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (TextUtils.isEmpty(mBody)) {
            return super.getBody();
        }else {
            return encodeStringParameters(mBody, getParamsEncoding());
        }
    }


    private byte[] encodeStringParameters(String jsonString, String paramsEncoding) {
        try {
            return jsonString.getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }

    protected String parameters2String(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
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
}
