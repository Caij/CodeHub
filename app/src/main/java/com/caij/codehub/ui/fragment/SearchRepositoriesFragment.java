package com.caij.codehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.caij.codehub.bean.Page;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.presenter.RepositoryListPresenter;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;
import com.caij.lib.utils.VolleyManager;

/**
 * Created by Caij on 2015/9/21.
 */
public class SearchRepositoriesFragment extends RepositoriesFragment{

    private String mRepoSearchQ;
    private String mRepoSort;
    private String mOrder;
    private RepositoryListPresenter mPresenter;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = PresenterFactory.newPresentInstance(RepositoryListPresenter.class);
    }

    @Override
    protected void onUserFirstVisible() {

    }

    public void search(String content) {

        if (TextUtils.isEmpty(content)) {
            getRecyclerViewAdapter().clearEntites();
            getLoadMoreRecyclerView().setState(LoadMoreRecyclerView.STATE_NO_MORE);
            getRecyclerViewAdapter().notifyDataSetChanged();
            return;
        }

        mRepoSearchQ = content;
        mRepoSort = "stars";
        mOrder = "desc";

        VolleyManager.cancelRequestByTag(getRequestTag());
        mPage = new Page();
        hideError();
        mPresenter.getSearchRepository(mRepoSearchQ, mRepoSort, mOrder, mPage, getRequestTag(), mFirstLoadUiCallBack);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getSearchRepository(mRepoSearchQ, mRepoSort, mOrder, mPage, getRequestTag(), mLoadMoreUiCallBack);
    }

    @Override
    public void onRefresh() {
        mPresenter.getSearchRepository(mRepoSearchQ, mRepoSort, mOrder, mPage.createRefreshPage(), getRequestTag(), mLoadRefreshUiCallBack);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mPresenter.getSearchRepository(mRepoSearchQ, mRepoSort, mOrder, mPage, getRequestTag(), mFirstLoadUiCallBack);
    }
}
