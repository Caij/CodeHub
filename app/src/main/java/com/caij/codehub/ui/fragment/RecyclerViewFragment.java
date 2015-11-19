package com.caij.codehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.caij.codehub.R;
import com.caij.codehub.bean.Entity;
import com.caij.codehub.ui.adapter.BaseAdapter;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;
import com.caij.codehub.widgets.recyclerview.RecyclerViewOnItemClickListener;

import butterknife.Bind;

/**
 * Created by Caij on 2015/9/23.
 */
public abstract class RecyclerViewFragment<E extends Entity> extends LazyFragment implements LoadMoreRecyclerView.OnLoadMoreListener, RecyclerViewOnItemClickListener {

    @Bind(R.id.recycler_view)
    LoadMoreRecyclerView mLoadMoreLoadMoreRecyclerView;

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

}
