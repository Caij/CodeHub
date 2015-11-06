package com.caij.codehub.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.ui.activity.RepositoryInfoActivity;
import com.caij.codehub.ui.adapter.BaseAdapter;
import com.caij.codehub.ui.adapter.RepositoryAdapter;
import com.caij.codehub.ui.intf.RepositoryListUi;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;

import java.util.List;

/**
 * Created by Caij on 2015/9/18.
 */
public abstract class RepositoriesFragment extends SwipeRefreshRecyclerViewFragment<Repository> implements RepositoryListUi {

    protected Page mPage;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPage = new Page();
    }

    @Override
    protected BaseAdapter<Repository> createRecyclerViewAdapter() {
        return new RepositoryAdapter(getContext(), null);
    }

    @Override
    protected LoadMoreRecyclerView.LayoutManager createRecyclerViewLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void onItemClick(View view, int position) {
        Repository repository = getRecyclerViewAdapter().getItem(position);
        Intent intent = RepositoryInfoActivity.newInstance(getActivity(),
                repository.getOwner().getLogin(), repository.getName());
        startActivity(intent);
    }

    @Override
    public void onFirstLoadSuccess(List<Repository> repositories) {
        super.onFirstLoadSuccess(repositories);
        mPage.next();
        getLoadMoreRecyclerView().setState(repositories.size() < mPage.getPageDataCount() ? LoadMoreRecyclerView.STATE_NO_MORE : LoadMoreRecyclerView.STATE_NORMAL);
    }

    @Override
    public void onRefreshSuccess(List<Repository> repositories) {
        super.onRefreshSuccess(repositories);
        mPage.reset();
        mPage.next();
        getLoadMoreRecyclerView().setState(repositories.size() < mPage.getPageDataCount() ? LoadMoreRecyclerView.STATE_NO_MORE : LoadMoreRecyclerView.STATE_NORMAL);
    }

    @Override
    public void onLoadMoreSuccess(List<Repository> repositories) {
        super.onLoadMoreSuccess(repositories);
        mPage.next();
        getLoadMoreRecyclerView().setState(repositories.size() < mPage.getPageDataCount() ? LoadMoreRecyclerView.STATE_NO_MORE : LoadMoreRecyclerView.STATE_NORMAL);
    }
}
