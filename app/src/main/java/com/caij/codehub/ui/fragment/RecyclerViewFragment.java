package com.caij.codehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.caij.codehub.R;
import com.caij.codehub.bean.Entity;
import com.caij.codehub.present.ui.RecyclerViewUi;
import com.caij.codehub.ui.adapter.BaseAdapter;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;
import com.caij.codehub.widgets.recyclerview.RecyclerViewOnItemClickListener;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Caij on 2015/9/23.
 */
public abstract class RecyclerViewFragment<E extends Entity> extends LazyFragment implements LoadMoreRecyclerView.OnLoadMoreListener, RecyclerViewOnItemClickListener, RecyclerViewUi<E> {

    @Bind(R.id.recycler_view)
    LoadMoreRecyclerView mLoadMoreLoadMoreRecyclerView;
    View mEmptyView;

    private BaseAdapter<E> mRecyclerViewAdapter;

    @Override
    protected int getAttachLayoutId() {
        return R.layout.include_recycle_view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContentContainer.setVisibility(View.GONE);
        mRecyclerViewAdapter  = createRecyclerViewAdapter();
        mRecyclerViewAdapter.setOnItemClickListener(this);
        mLoadMoreLoadMoreRecyclerView.setLayoutManager(createRecyclerViewLayoutManager());
        mLoadMoreLoadMoreRecyclerView.setOnLoadMoreListener(this);
        mLoadMoreLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    protected abstract BaseAdapter<E> createRecyclerViewAdapter();

    protected abstract LoadMoreRecyclerView.LayoutManager createRecyclerViewLayoutManager();

    public BaseAdapter<E> getRecyclerViewAdapter() {
        return mRecyclerViewAdapter;
    }

    public LoadMoreRecyclerView getLoadMoreRecyclerView() {
        return mLoadMoreLoadMoreRecyclerView;
    }

    public void showEmptyView(boolean isVisible) {
        if (isVisible) {
            if (mEmptyView == null) {
                getActivity().getLayoutInflater().inflate(R.layout.include_empty_view, mContentContainer, true);
                mEmptyView = mContentContainer.findViewById(R.id.ll_empty_view);
            }
            mEmptyView.setVisibility(View.VISIBLE);
        }else {
            if (mEmptyView != null) {
                mEmptyView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onFirstLoadSuccess(List<E> entities) {
        showContentContainer();
        showEmptyView(entities.size() ==  0);
        getRecyclerViewAdapter().setEntities(entities);
        getRecyclerViewAdapter().notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreSuccess(List<E> entities) {
        getLoadMoreRecyclerView().completeLoading();
        getRecyclerViewAdapter().addEntities(entities);
        getRecyclerViewAdapter().notifyDataSetChanged();
    }

    @Override
    public void onFirstLoadError(int msgId) {
        showError(msgId);
    }

    @Override
    public void onLoadMoreError(int msgId) {
        showError(msgId);
        getLoadMoreRecyclerView().completeLoading();
    }

}
