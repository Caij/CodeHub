package com.caij.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Caij on 2015/8/26.
 */
public class SPUtils {

    private static SharedPreferences sp;

    public static void init(Context context, String spName) {
        sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    public static void save(String name, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public static String get(String name, String defaultValue) {
        return sp.getString(name, defaultValue);
    }
}
