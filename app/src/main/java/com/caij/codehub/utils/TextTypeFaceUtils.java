package com.caij.codehub.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Caij on 2015/9/19.
 */
public class TextTypeFaceUtils {

    private static Typeface mTypeface;

    public static Typeface getGithubTypeFace(Context context) {
        if (mTypeface == null) {
            mTypeface = Typeface.createFromAsset(context.getAssets(), "octicons.ttf");
        }
        return mTypeface;
    }
}
