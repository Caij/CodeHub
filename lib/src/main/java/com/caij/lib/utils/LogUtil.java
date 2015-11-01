package com.caij.lib.utils;

import android.text.TextUtils;
import android.util.Log;

import com.caij.lib.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * log  util
 */
public class LogUtil {

    private final static boolean LOG_SWITCH = true;

    /**
     * It is used for json pretty print
     */
    private static final int JSON_INDENT = 4;

    public static void e(String tag, String msg) {
        if (LOG_SWITCH) {
            Log.e(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LOG_SWITCH) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LOG_SWITCH) {
            Log.d(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (LOG_SWITCH) {
            Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LOG_SWITCH) {
            Log.w(tag, msg);
        }
    }

    public static void wtf(String tag, String msg) {
        if (LOG_SWITCH) {
            Log.wtf(tag, msg);
        }
    }

    public static void json(String tag, String msg) {
        if (LOG_SWITCH) {
            if (TextUtils.isEmpty(msg)) {
                Log.e(tag, "msg is null.");
                return;
            }
            try {
                if (msg.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(msg);
                    String message = jsonObject.toString(JSON_INDENT);
                    String[] lines = message.split(System.getProperty("line.separator"));
                    Log.d(tag, "┌───────────────────────────────────────────────────────────────────────────────────────");
                    for (String line : lines) {
                        Log.d(tag, "│" + " " + line);
                    }
                    Log.d(tag, "└───────────────────────────────────────────────────────────────────────────────────────");
                    return;
                }
                if (msg.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(msg);
                    String message = jsonArray.toString(JSON_INDENT);
                    String[] lines = message.split(System.getProperty("line.separator"));
                    Log.d(tag, "┌───────────────────────────────────────────────────────────────────────────────────────");
                    for (String line : lines) {
                        Log.d(tag, "│" + " " + line);
                    }
                    Log.d(tag, "└───────────────────────────────────────────────────────────────────────────────────────");
                }
            } catch (JSONException e) {
                Log.e(tag, e.getCause().getMessage() + "\n" + msg);
            }
        }
    }

}
