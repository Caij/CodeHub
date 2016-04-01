package com.caij.util;

import android.text.TextUtils;

/**
 * Created by Caij on 2015/9/18.
 */
public class CheckValueUtil {

    public static void check(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("the value not be null");
        }
        if (object instanceof CharSequence && TextUtils.isEmpty((CharSequence) object)) {
            throw new IllegalArgumentException("the value not be null");
        }
    }
}
