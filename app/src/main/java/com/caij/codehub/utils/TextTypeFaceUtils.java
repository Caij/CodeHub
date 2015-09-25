package com.caij.codehub.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Caij on 2015/9/19.
 */
public class TextTypeFaceUtils {

    private static Typeface mTypeface;

    public static void initGithubTextTypeFace(Context context){
        mTypeface = Typeface.createFromAsset(context.getAssets(), "octicons.ttf");
    }

    public static Typeface getGithubTypeFace() {
        return mTypeface;
    }
}
