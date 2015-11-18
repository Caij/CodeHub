package com.caij.codehub.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/17.
 */
public class SpannedUtils {

    public static final String REPOSITORY_START = "<r>";
    public static final String REPOSITORY_END = "<r>";

    public static Spanned string2Spanned(String content, int color, View.OnClickListener onClickListener) {
        SpannableString spannableString = new SpannableString(content);
        ClickableSpanned spanned = new ClickableSpanned(color, onClickListener);
        spannableString.setSpan(spanned, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    static private class ClickableSpanned extends ClickableSpan {

        int color;
        View.OnClickListener onClickListener;

        public ClickableSpanned(int color, View.OnClickListener onClickListener) {
            this.color = color;
            this.onClickListener = onClickListener;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(color);
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            if (onClickListener != null) {
                onClickListener.onClick(widget);
            }
        }
    }
}
