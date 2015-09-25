package com.caij.lib.utils;

import android.content.Context;
import android.widget.Toast;

import com.caij.lib.R;

/**
 * Created by Caij on 2015/8/25.
 */
public class ToastUtil {
    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, int id) {
        Toast.makeText(context, context.getString(id), Toast.LENGTH_SHORT).show();
    }
}
