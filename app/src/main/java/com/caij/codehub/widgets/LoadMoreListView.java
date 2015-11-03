package com.caij.codehub.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.caij.codehub.R;

/**
 * Created by Caij on 2015/9/23.
 */
public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {

    private LayoutInflater mInflater;
    private View mFooterView;
    private View mhintLoadMore;
    private View mProgressBarLoadMore;
    private OnScrollListener mOnScrollListener;
    private int mCurrentScrollState;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean mIsCanLoadMore = true;
    private boolean mIsLoadingMore;
    private boolean mIsNoMore;
    private View mFooterViewContent;

    public LoadMoreListView(Context context) {
        super(context);
        init(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // footer
        mFooterView =  mInflater.inflate(R.layout.include_load_more, this, false);
        mhintLoadMore =  mFooterView.findViewById(R.id.tv_hint);
        mProgressBarLoadMore = mFooterView.findViewById(R.id.pb_loading);
        mFooterViewContent = mFooterView.findViewById(R.id.content);

        addFooterView(mFooterView);

        super.setOnScrollListener(this);
    }


    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        if (mOnScrollListener != null) {
            mOnScrollListener.onScroll(view, firstVisibleItem,
                    visibleItemCount, totalItemCount);
        }

        if(mIsCanLoadMore) {
            if (visibleItemCount == totalItemCount) {
                //这里避免数据item未超过1屏幕，如果显示加载很多  丑
                mFooterViewContent.setVisibility(GONE);
                return;
            }

            mFooterViewContent.setVisibility(VISIBLE);
            boolean loadMore = firstVisibleItem + visibleItemCount >= totalItemCount;

            if (!mIsLoadingMore && loadMore && !mIsNoMore
                    && mCurrentScrollState != SCROLL_STATE_IDLE) {
                    mProgressBarLoadMore.setVisibility(View.VISIBLE);
                    mhintLoadMore.setVisibility(View.GONE);
                    mIsLoadingMore = true;
                    onLoadMore();
            }
        }
    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        mCurrentScrollState = scrollState;
        if (mOnScrollListener != null) {
            mOnScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void setOnScrollListener(OnScrollListener listener) {
        mOnScrollListener = listener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

    public void setNoMore(boolean isNoMore) {
        if (mIsCanLoadMore) {
            mIsNoMore = isNoMore;
            if (isNoMore) {
                mhintLoadMore.setVisibility(View.VISIBLE);
                mProgressBarLoadMore.setVisibility(GONE);
            } else {
                mhintLoadMore.setVisibility(View.GONE);
                mProgressBarLoadMore.setVisibility(VISIBLE);
            }
        }
    }

    public void setCanLoadMore(boolean canLoadMore) {
        mIsCanLoadMore = canLoadMore;
        if (mIsCanLoadMore){
            addFooterView(mFooterView);
        }else {
            removeFooterView(mFooterView);
        }
    }

    public void onLoadMore() {
        if (mOnLoadMoreListener != null) {
            mOnLoadMoreListener.onLoadMore();
        }
    }

    /**
     * Notify the loading more operation has finished
     */
    public void onLoadMoreComplete() {
        mIsLoadingMore = false;
        mProgressBarLoadMore.setVisibility(View.GONE);
        mhintLoadMore.setVisibility(View.GONE);
    }

    /**
     * Interface definition for a callback to be invoked when list reaches the
     * last item (the user load more items in the list)
     */
    public interface OnLoadMoreListener {
        /**
         * Called when the list reaches the last item (the last item is visible
         * to the user)
         */
        public void onLoadMore();
    }
}
