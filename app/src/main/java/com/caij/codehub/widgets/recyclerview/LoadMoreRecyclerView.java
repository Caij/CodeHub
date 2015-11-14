package com.caij.codehub.widgets.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;


/**
 * Created by Caij on 2015/11/4.
 */
public class LoadMoreRecyclerView extends RecyclerView {

    public static final int STATE_NORMAL = 1;
    public static final int STATE_LOADING = 2;
    public static final int STATE_NO_MORE = 3;
    private static final String TAG = "RecyclerView";

    private OnLoadMoreListener mOnLoadMoreListener;
    private int mState = STATE_NORMAL;
    private boolean mIsLoadMoreEnable = true;

    public LoadMoreRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        addOnScrollListener(mOpOnScrollChangeListener);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    private void onLoadMore() {
        if (mState == STATE_NORMAL && mOnLoadMoreListener != null) {
            setState(STATE_LOADING);
            mOnLoadMoreListener.onLoadMore();
        }
    }

    public void completeLoading() {
        setState(STATE_NORMAL);
    }

    public static interface OnLoadMoreListener {
        public void onLoadMore();
    }

    public void setState(int state) {
        if (mIsLoadMoreEnable) {
            this.mState = state;
            if (getAdapter() instanceof LoadMoreRecyclerViewAdapter) {
                LoadMoreRecyclerViewAdapter baseAdapter = (LoadMoreRecyclerViewAdapter) getAdapter();
                baseAdapter.setState(state);
            }
        }
    }

    public void setLoadMoreEnable(boolean enable) {
        if (!enable) setState(STATE_NO_MORE);
        this.mIsLoadMoreEnable = enable;
    }

    private OnScrollListener mOpOnScrollChangeListener = new OnScrollListener(){
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (mState != STATE_NORMAL && !mIsLoadMoreEnable) return;

            LayoutManager layoutManager = getLayoutManager();
            final int visibleItemCount = getChildCount();
            final int totalItemCount = layoutManager.getItemCount();

            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                final int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                if ((totalItemCount - visibleItemCount) <= firstVisibleItem ) {
                    onLoadMore();
                }
            }else if (layoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                int lastVisiblePisitoin = staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(null)[1];
                if (lastVisiblePisitoin == totalItemCount) {
                    onLoadMore();
                }
            }
        }
    };

}
