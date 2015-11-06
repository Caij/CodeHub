package com.caij.codehub.widgets.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.caij.codehub.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/11/5.
 */
public abstract class LoadMoreRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int TYPE_LOADING = 1;

    protected Context context;

    protected LayoutInflater mInflater;

    private int mState;

    private LoadingMoreHolder mLoadingMoreHolder;
    private RecyclerViewOnItemClickListener onItemClickListener;

    public LoadMoreRecyclerViewAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LOADING) {
            return new LoadingMoreHolder(
                    mInflater.inflate(R.layout.infinite_loading, parent, false));
        }
        return onCreateDataViewHolder(parent, viewType);
    }


    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (position == getDataCount()) {
            onBindLoadingViewHolder(holder, position);
        } else {
            onBindDataViewHolder(holder, position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, position);
                    }
                }
            });
        }
    }

    public void setOnItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void onBindLoadingViewHolder(RecyclerView.ViewHolder holder, int position) {
        mLoadingMoreHolder = (LoadingMoreHolder) holder;
        setState(mState);
    }


    public abstract RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindDataViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public final int getItemCount() {
        return getDataCount() + 1;
    }

    @Override
    public final int getItemViewType(int position) {
        if (position == getDataCount()) {
            return TYPE_LOADING;
        }
        return getDataViewType(position);
    }

    public int getDataViewType(int position) {
        return 2;
    }

    public abstract int getDataCount();

    public void setState(int state) {
        mState = state;
        if (mLoadingMoreHolder == null) return;
        mLoadingMoreHolder.mHint.setVisibility(View.VISIBLE);
        mLoadingMoreHolder.mLoading.setVisibility(View.VISIBLE);
        mLoadingMoreHolder.mView.setVisibility(View.VISIBLE);
        switch (state) {
            case LoadMoreRecyclerView.STATE_NORMAL:
                mLoadingMoreHolder.mLoading.setVisibility(View.INVISIBLE);
                mLoadingMoreHolder.mHint.setText(context.getString(R.string.pull_loading));
                break;
            case LoadMoreRecyclerView.STATE_LOADING:
                mLoadingMoreHolder.mLoading.setVisibility(View.VISIBLE);
                mLoadingMoreHolder.mHint.setText(context.getString(R.string.loading));
                break;
            case LoadMoreRecyclerView.STATE_NO_MORE:
                mLoadingMoreHolder.mView.setVisibility(View.GONE);
                mLoadingMoreHolder.mHint.setVisibility(View.GONE);
                mLoadingMoreHolder.mLoading.setVisibility(View.GONE);
                break;

        }
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'infinite_loading.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class LoadingMoreHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.hint)
        TextView mHint;
        @Bind(R.id.loading)
        ProgressBar mLoading;
        @Bind(R.id.content)
        View mView;

        LoadingMoreHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
