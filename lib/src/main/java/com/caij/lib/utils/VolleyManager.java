package com.caij.lib.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;

/**
 * Created by Caij on 2015/8/25.
 */
public class VolleyManager {

    private static RequestQueue mQueue;

    public static void init(Context context, HttpStack stack, int diskCacheSize) {
        if (mQueue == null) {
            synchronized (VolleyManager.class) {
                if (mQueue == null) {
                    mQueue = Volley.newRequestQueue(context, stack, diskCacheSize);
                }
            }
        }
    }

    public static void addRequest(Request request, Object tag) {
        checkQueue();
        request.setTag(tag);
        mQueue.add(request);
    }

    public static void cancelRequestByTag(Object tag) {
        checkQueue();
        mQueue.cancelAll(tag);
    }

    private static void checkQueue() {
        if (mQueue == null) {
            throw new IllegalArgumentException("RequestQueue not init");
        }
    }

}
