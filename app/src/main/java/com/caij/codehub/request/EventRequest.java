package com.caij.codehub.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.caij.codehub.bean.event.Event;
import com.caij.codehub.bean.event.EventWrap;
import com.caij.lib.utils.GsonUtils;
import com.caij.lib.volley.request.GsonRequest;
import com.caij.lib.volley.request.JsonParseError;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Caij on 2015/10/30.
 */
public class EventRequest extends GsonRequest<List<Event>>{

    public EventRequest(int method, String url, Response.Listener<List<Event>> response, Response.ErrorListener listener) {
        super(method, url, null, response, listener);
    }

    public EventRequest(int method, String url, Map<String, String> params, Response.Listener<List<Event>> response, Response.ErrorListener listener) {
        super(method, url, params, null, response, listener);
    }

    public EventRequest(int method, String url, String params,Response.Listener<List<Event>> response, Response.ErrorListener listener) {
        super(method, url, params, null, response, listener);
    }

    public EventRequest(int method, String url, Map<String, String> params, Map<String, String> head, Response.Listener<List<Event>> response, Response.ErrorListener listener) {
        super(method, url, params, head, null, response, listener);
    }

    public EventRequest(int method, String url, String params, Map<String, String> head, Response.Listener<List<Event>> response, Response.ErrorListener listener) {
        super(method, url, params, head, null, response, listener);
    }

    public EventRequest(int method, String url, Map<String, String> params, Map<String, String> head, String bodyContentType, Response.Listener<List<Event>> response, Response.ErrorListener listener) {
        super(method, url, params, head, bodyContentType, null, response, listener);
    }

    public EventRequest(int method, String url, String params, Map<String, String> head, String bodyContentType, Response.Listener<List<Event>> response, Response.ErrorListener listener) {
        super(method, url, params, head, bodyContentType, null, response, listener);
    }

    @Override
    protected Response<List<Event>> parseNetworkResponse(NetworkResponse response) {
        logResult(response);
        List<Event> result;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(response.data);
        try {
            result = GsonUtils.getGson().fromJson(new InputStreamReader(byteArrayInputStream, HttpHeaderParser.parseCharset(response.headers)), new TypeToken<List<Event>>(){}.getType());
            convert(result);
            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            result = GsonUtils.getGson().fromJson(new InputStreamReader(byteArrayInputStream), new TypeToken<List<Event>>(){}.getType());
            convert(result);
            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        }
    }

    public void convert(List<Event> events) {
        if (events != null) {
            for (Event event : events) {
                EventWrap.convert(event);
            }
        }
    }
}
