package com.caij.codehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.VolleyError;
import com.caij.codehub.R;
import com.caij.codehub.bean.Entity;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.presenter.Present;
import com.caij.codehub.ui.callback.UiCallBack;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Caij on 2015/11/4.
 */
public abstract class SwipeRefreshRecyclerViewFragment<E extends Entity> extends RecyclerViewFragment<E> implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected int getContentLayoutId() {
        return R.layout.include_refresh_recycle_view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.gplus_color_1),
                getResources().getColor(R.color.gplus_color_2),
                getResources().getColor(R.color.gplus_color_3),
                getResources().getColor(R.color.gplus_color_4));
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
    public void onLoadMoreSuccess(List<E>  entities) {
        getLoadMoreRecyclerView().completeLoading();
        getRecyclerViewAdapter().addEntities(entities);
        getRecyclerViewAdapter().notifyDataSetChanged();
    }

    @Override
    public void onRefreshError(VolleyError error) {
        mSwipeRefreshLayout.setRefreshing(false);
        processVolleyError(error);
    }

    @Override
    public void onLoadMoreError(VolleyError error) {
        getLoadMoreRecyclerView().completeLoading();
        processVolleyError(error);
    }

    @Override
    public void onFirstLoadError(VolleyError error) {
        hideLoading();
        processVolleyError(error);
        showError();
    }

//    @Override
//    public void onLoadMore() {
//        mSwipeRefreshLayout.setRefreshing(false);
//    }
//
//    @Override
//    public void onRefresh() {
//
//    }

    @Override
    public void onComnLoading(int loadType) {
        if (loadType == LOAD_FIRST) {
            showLoading();
        }
    }

    public void onComnLoadSuccess(int loadType, List<E> entities) {
        if (loadType == LOAD_FIRST) {
            onFirstLoadSuccess(entities);
        }else if (loadType == LOAD_REFRESH) {
            onRefreshSuccess(entities);
        }else if (loadType == LOAD_MORE) {
            onLoadMoreSuccess(entities);
        }
    }

    public void onComnLoadError(int loadType, VolleyError error) {
        if (loadType == LOAD_FIRST) {
            onFirstLoadError(error);
        }else if (loadType == LOAD_REFRESH) {
            onRefreshError(error);
        }else if (loadType == LOAD_MORE) {
            onLoadMoreError(error);
        }
    }


    protected UiCallBack<List<E>> mFirstLoadUiCallBack = new UiCallBack<List<E>>() {
        @Override
        public void onSuccess(List<E> entities) {
            onFirstLoadSuccess(entities);
        }

        @Override
        public void onLoading() {
            onComnLoading(LOAD_FIRST);
        }

        @Override
        public void onError(VolleyError error) {
            onFirstLoadError(error);
        }
    };

    protected UiCallBack<List<E>> mLoadMoreUiCallBack = new UiCallBack<List<E>>() {
        @Override
        public void onSuccess(List<E> entities) {
            onLoadMoreSuccess(entities);
        }

        @Override
        public void onLoading() {
            onComnLoading(LOAD_MORE);
        }

        @Override
        public void onError(VolleyError error) {
            onLoadMoreError(error);
        }
    };

    protected UiCallBack<List<E>> mLoadRefreshUiCallBack = new UiCallBack<List<E>>() {
        @Override
        public void onSuccess(List<E> entities) {
            onRefreshSuccess(entities);
        }

        @Override
        public void onLoading() {
            onComnLoading(LOAD_REFRESH);
        }

        @Override
        public void onError(VolleyError error) {
            onRefreshError(error);
        }
    };

}
