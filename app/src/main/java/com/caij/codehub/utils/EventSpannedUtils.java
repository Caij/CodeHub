package com.caij.codehub.utils;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/17.
 */
public class EventSpannedUtils {

    public final static String LINK_COLOR = "#0066B3";

    public static EventBodySpannableStringBuild parseRepository(String content, EventBodySpannableStringBuild build) {
        SpannableString spannableString = new SpannableString(content);
        ClickableSpanned span = new ClickableSpanned(content);
        spannableString.setSpan(span, 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        build.append(spannableString);
        build.mRepositoryClickableSpanned = span;
        return build;
    }

    public static EventBodySpannableStringBuild parseUser(String content, EventBodySpannableStringBuild build) {
        SpannableString spannableString = new SpannableString(content);
        ClickableSpanned span = new ClickableSpanned(content);
        spannableString.setSpan(span, 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        build.append(spannableString);
        build.mUserClickableSpanned = span;
        return build;
    }

    public static EventBodySpannableStringBuild parseIssueNum(String content, EventBodySpannableStringBuild build) {
        SpannableString spannableString = new SpannableString(content);
        ClickableSpanned span = new ClickableSpanned(content);
        spannableString.setSpan(span, 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        build.append(spannableString);
        build.mIssueClickableSpanned = span;
        return build;
    }

    static private class ClickableSpanned extends ClickableSpan {

        OnClickableSpannedClickListener onClickListener;
        String content;

        public ClickableSpanned(String content) {
            this.content = content;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.parseColor(LINK_COLOR));
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            if (onClickListener != null) {
                onClickListener.onClick(widget, content);
            }
        }

        public void setOnClickListener(OnClickableSpannedClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }
    }

    public static class EventBodySpannableStringBuild extends SpannableStringBuilder {

        private ClickableSpanned mRepositoryClickableSpanned;
        private ClickableSpanned mUserClickableSpanned;
        private ClickableSpanned mIssueClickableSpanned;


        public void setRepositoryClickListener(OnClickableSpannedClickListener onClickListener) {
            if (mRepositoryClickableSpanned != null) {
                mRepositoryClickableSpanned.setOnClickListener(onClickListener);
            }
        }

        public void setUserClickListener(OnClickableSpannedClickListener onClickListener) {
            if (mUserClickableSpanned != null) {
                mUserClickableSpanned.setOnClickListener(onClickListener);
            }
        }

        public void setIssueClickListener(OnClickableSpannedClickListener onClickListener) {
            if (mIssueClickableSpanned != null) {
                mIssueClickableSpanned.setOnClickListener(onClickListener);
            }
        }

        public OnClickableSpannedClickListener getRepositoryClickListener() {
            return mRepositoryClickableSpanned.onClickListener;
        }

        public OnClickableSpannedClickListener getUserClickListener() {
            return mUserClickableSpanned.onClickListener;
        }

        public OnClickableSpannedClickListener getIssueClickListener() {
            if (mIssueClickableSpanned != null) {
                return mIssueClickableSpanned.onClickListener;
            }else {
                return null;
            }
        }

        public boolean isSupportIssueClickListener() {
            return mIssueClickableSpanned != null;
        }
    }

    public static interface OnClickableSpannedClickListener {
        public void onClick(View view, String content);
    }

}
