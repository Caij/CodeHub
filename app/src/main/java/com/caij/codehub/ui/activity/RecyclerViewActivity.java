package com.caij.codehub.ui.activity;


import android.os.Bundle;

import com.caij.codehub.R;
import com.caij.codehub.bean.Entity;
import com.caij.codehub.ui.adapter.BaseAdapter;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;
import com.caij.codehub.widgets.recyclerview.RecyclerViewOnItemClickListener;

import butterknife.Bind;

/**
 * Created by Caij on 2015/11/4.
 */
public abstract class RecyclerViewActivity<E extends Entity> extends BaseCodeHubToolBarActivity implements LoadMoreRecyclerView.OnLoadMoreListener, RecyclerViewOnItemClickListener {

    public static final int LOAD_FIRST = 1;
    public static final int LOAD_REFRESH = 2;
    public static final int LOAD_MORE = 3;


    @Bind(R.id.recycler_view)
    LoadMoreRecyclerView mLoadMoreLoadMoreRecyclerView;

    private BaseAdapter<E> mRecyclerViewAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.include_recycle_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    protected void initView() {
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
