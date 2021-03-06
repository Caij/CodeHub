package com.caij.codehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.caij.codehub.present.LoadType;
import com.caij.codehub.present.RepositoriesPresent;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;

/**
 * Created by Caij on 2015/9/21.
 */
public class SearchRepositoriesFragment extends RepositoriesFragment{

    private String mRepoSearchQ;
    private String mRepoSort;
    private String mOrder;
    private RepositoriesPresent mRepositoriesPresent;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRepositoriesPresent = new RepositoriesPresent(this);
        showContentContainer();
        getLoadMoreRecyclerView().setState(LoadMoreRecyclerView.STATE_NO_MORE);
    }

    @Override
    protected void onUserFirstVisible() {

    }

    public void search(String content) {

        if (TextUtils.isEmpty(content)) {
            getRecyclerViewAdapter().clearEntities();
            getLoadMoreRecyclerView().setState(LoadMoreRecyclerView.STATE_NO_MORE);
            getRecyclerViewAdapter().notifyDataSetChanged();
            return;
        }

        mRepoSearchQ = content;
        mRepoSort = "stars";
        mOrder = "desc";

        hideError();
        mSwipeRefreshLayout.setRefreshing(true);
        mRepositoriesPresent.getSearchRepository(LoadType.REFRESH, mRepoSearchQ, mRepoSort, mOrder, mPage.createRefreshPage());
    }

    @Override
    public void onLoadMore() {
        mRepositoriesPresent.getSearchRepository(LoadType.LOADMORE, mRepoSearchQ, mRepoSort, mOrder, mPage);
    }

    @Override
    public void onRefresh() {
        mRepositoriesPresent.getSearchRepository(LoadType.REFRESH, mRepoSearchQ, mRepoSort, mOrder, mPage.createRefreshPage());
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mSwipeRefreshLayout.setRefreshing(true);
        mRepositoriesPresent.getSearchRepository(LoadType.REFRESH, mRepoSearchQ, mRepoSort, mOrder, mPage);
    }

    @Override
    public void onDestroyView() {
        mRepositoriesPresent.onDeath();
        super.onDestroyView();
    }
}
