package com.caij.codehub.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.caij.codehub.utils.TextTypeFaceUtils;

/**
 * Created by Caij on 2015/10/29.
 */
public class GithubTypeFaceTextView extends TextView{
    public GithubTypeFaceTextView(Context context) {
        super(context);
        initTypeFace(context);
    }

    public GithubTypeFaceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeFace(context);
    }

    public GithubTypeFaceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeFace(context);
    }

    private void initTypeFace(Context context) {
        setTypeface(TextTypeFaceUtils.getGithubTypeFace(context));
    }
}
