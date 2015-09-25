package com.caij.lib.utils;

import com.google.gson.Gson;

/**
 * Created by Caij on 2015/8/24.
 */
public class GsonUtils {

    private static Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }
}
