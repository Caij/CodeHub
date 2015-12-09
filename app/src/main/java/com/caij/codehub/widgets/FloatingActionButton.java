package com.caij.codehub.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Caij on 2015/12/8.
 */
public class FloatingActionButton extends android.support.design.widget.FloatingActionButton{
    public FloatingActionButton(Context context) {
        super(context);
    }

    public FloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void hide() {

    }

    @Override
    public void hide(@Nullable OnVisibilityChangedListener listener) {

    }

    @Override
    public void show() {
    }

    @Override
    public void show(@Nullable OnVisibilityChangedListener listener) {
    }
}
