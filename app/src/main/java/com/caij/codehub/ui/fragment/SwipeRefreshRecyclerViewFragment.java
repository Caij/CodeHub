package com.caij.codehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.caij.codehub.R;
import com.caij.codehub.bean.Entity;
import com.caij.codehub.present.ui.ListUi;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Caij on 2015/11/4.
 */
public abstract class SwipeRefreshRecyclerViewFragment<E extends Entity> extends RecyclerViewFragment<E> implements ListUi<E>, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    View mEmptyView;

    @Override
    protected int getAttachLayoutId() {
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
    public void onRefreshSuccess(List<E> entities) {
        mSwipeRefreshLayout.setRefreshing(false);
        showEmptyView(entities.size() ==  0);
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
    public void onFirstLoadError(int msgId) {
        showError(msgId);
    }

    @Override
    public void onRefreshError(int msgId) {
        showError(msgId);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMoreError(int msgId) {
        showError(msgId);
        getLoadMoreRecyclerView().completeLoading();
    }

}
