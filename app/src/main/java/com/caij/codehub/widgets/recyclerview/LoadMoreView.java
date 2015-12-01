package com.caij.codehub.widgets.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.caij.codehub.R;
import com.caij.codehub.utils.DensityUtil;

/**
 * Author:  Caij
 * Email:   worldcaij@gmail.com
 * Date:    2015/11/21
 * Description:
 */
public class LoadMoreView extends RelativeLayout{

    public static final int STATE_NORMAL = 1;
    public static final int STATE_LOADING = 2;
    public static final int STATE_NO_MORE = 3;

    private int mState;
    private ProgressBar mLoading;
    private TextView mHint;
    private View mLoadingContent;

    public LoadMoreView(Context context) {
        super(context);
        init(context);
    }

    public LoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.infinite_loading, this);
        mLoading = (ProgressBar) findViewById(R.id.loading);
        mHint = (TextView) findViewById(R.id.hint);
        mLoadingContent = findViewById(R.id.ll_loading_container);
    }

    public void setState(int state) {
        this.mState = state;
        switch (state) {
            case STATE_NORMAL:
                mLoadingContent.setVisibility(VISIBLE);
                mLoading.setVisibility(View.GONE);
                mHint.setVisibility(VISIBLE);
                mHint.setText(getContext().getString(R.string.pull_loading));
                break;
            case STATE_LOADING:
                mLoadingContent.setVisibility(VISIBLE);
                mLoading.setVisibility(View.VISIBLE);
                mHint.setVisibility(VISIBLE);
                mHint.setText(getContext().getString(R.string.loading));
                break;
            case STATE_NO_MORE:
                mHint.setVisibility(View.GONE);
                mLoading.setVisibility(View.GONE);
                mLoadingContent.setVisibility(GONE);
                break;
        }
    }

    public int getState() {
        return mState;
    }

}
