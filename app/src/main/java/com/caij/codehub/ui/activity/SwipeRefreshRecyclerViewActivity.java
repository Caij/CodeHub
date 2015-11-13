package com.caij.codehub.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.android.volley.VolleyError;
import com.caij.codehub.R;
import com.caij.codehub.bean.Entity;
import com.caij.codehub.ui.callback.DefaultUiCallBack;
import com.caij.codehub.ui.callback.UiCallBack;
import com.caij.codehub.ui.callback.ListUi;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Caij on 2015/11/4.
 */
public abstract class SwipeRefreshRecyclerViewActivity<E extends Entity>  extends RecyclerViewActivity<E> implements SwipeRefreshLayout.OnRefreshListener,ListUi<E> {

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    protected UiCallBack<List<E>> mFirstLoadUiCallBack;
    protected UiCallBack<List<E>> mLoadMoreUiCallBack;
    protected UiCallBack<List<E>> mLoadRefreshUiCallBack;

    @Override
    protected int getContentLayoutId() {
        return R.layout.include_refresh_recycle_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        mContentContainer.setVisibility(View.GONE);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.gplus_color_1),
                getResources().getColor(R.color.gplus_color_2),
                getResources().getColor(R.color.gplus_color_3),
                getResources().getColor(R.color.gplus_color_4));

        initLoadDataCallback();
    }


    @Override
    public void onFirstLoadSuccess(List<E> entities) {
        hideLoading();
        showContentContainer();
        getRecyclerViewAdapter().setEntities(entities);
        getRecyclerViewAdapter().notifyDataSetChanged();
    }

    @Override
    public void onRefreshSuccess(List<E> entities) {
        mSwipeRefreshLayout.setRefreshing(false);
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
    public void onRefreshError(VolleyError error) {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMoreError(VolleyError error) {
        getLoadMoreRecyclerView().setState(LoadMoreRecyclerView.STATE_NORMAL);
    }

    @Override
    public void onFirstLoadError(VolleyError error) {
        hideLoading();
        showError();
    }

    @Override
    public void onComnLoading(int loadType) {
        if (loadType == LOAD_FIRST) {
            showLoading();
        }
    }


    protected void initLoadDataCallback() {
        mLoadRefreshUiCallBack = new DefaultUiCallBack<List<E>>(this) {
            @Override
            public void onSuccess(List<E> entities) {
                onRefreshSuccess(entities);
            }

            @Override
            public void onLoading() {
                onComnLoading(LOAD_REFRESH);
            }

            @Override
            public void onDefaultError(VolleyError error) {
                onRefreshError(error);
            }
        };

        mLoadMoreUiCallBack = new DefaultUiCallBack<List<E>>(this) {
            @Override
            public void onSuccess(List<E> entities) {
                onLoadMoreSuccess(entities);
            }

            @Override
            public void onLoading() {
                onComnLoading(LOAD_MORE);
            }

            @Override
            public void onDefaultError(VolleyError error) {
                onLoadMoreError(error);
            }
        };

        mFirstLoadUiCallBack = new DefaultUiCallBack<List<E>>(this) {
            @Override
            public void onSuccess(List<E> entities) {
                onFirstLoadSuccess(entities);
            }

            @Override
            public void onLoading() {
                onComnLoading(LOAD_FIRST);
            }


            @Override
            public void onDefaultError(VolleyError error) {
                onFirstLoadError(error);
            }
        };
    }
}
