package com.caij.codehub.utils;

import android.content.Context;

/**
 * Author:  Caij
 * Email:   worldcaij@gmail.com
 * Date:    2015/11/21
 * Description:
 */
public class DensityUtil {


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
