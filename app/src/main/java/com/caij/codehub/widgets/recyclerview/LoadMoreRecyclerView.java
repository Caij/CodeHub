package com.caij.codehub.widgets.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.caij.codehub.R;


/**
 * Created by Caij on 2015/11/4.
 */
public class LoadMoreRecyclerView extends RecyclerView {

    private static final String TAG = "RecyclerView";

    public static final int STATE_NORMAL = 1;
    public static final int STATE_LOADING = 2;
    public static final int STATE_NO_MORE = 3;

    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean mIsLoadMoreEnable = true;
    private LoadMoreView mLoadMoreView;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;


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

    @Override
    public void setAdapter(Adapter adapter) {
        HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(adapter);
        setAdapter(headerAndFooterRecyclerViewAdapter);
    }

    public void setAdapter(HeaderAndFooterRecyclerViewAdapter adapter)  {
        mHeaderAndFooterRecyclerViewAdapter = adapter;
        if (mIsLoadMoreEnable) {
            mLoadMoreView = new LoadMoreView(getContext());
            mHeaderAndFooterRecyclerViewAdapter.addFooterView(mLoadMoreView);
        }
        super.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
    }

    private void onLoadMore() {
        if (mIsLoadMoreEnable && mLoadMoreView != null && mLoadMoreView.getState() == LoadMoreView.STATE_NORMAL
                && mOnLoadMoreListener != null) {
            setState(LoadMoreView.STATE_LOADING);
            mOnLoadMoreListener.onLoadMore();
        }
    }

    public void completeLoading() {
        setState(LoadMoreView.STATE_NORMAL);
    }

    public static interface OnLoadMoreListener {
        public void onLoadMore();
    }

    public void setState(int state) {
        if (mIsLoadMoreEnable && mLoadMoreView != null) {
            mLoadMoreView.setState(state);
        }
        if (state == LoadMoreView.STATE_NO_MORE) {
            mHeaderAndFooterRecyclerViewAdapter.removeFooterView(mLoadMoreView);
        }
    }

    public void setLoadMoreEnable(boolean enable) {
        if (!enable) setState(LoadMoreView.STATE_NO_MORE);
        this.mIsLoadMoreEnable = enable;
    }

    private OnScrollListener mOpOnScrollChangeListener = new OnScrollListener(){
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (!mIsLoadMoreEnable && mLoadMoreView.getState() != LoadMoreView.STATE_NORMAL) return;

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
                int lastVisiblePosition = staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(null)[1];
                if (lastVisiblePosition == totalItemCount) {
                    onLoadMore();
                }
            }
        }
    };

}
