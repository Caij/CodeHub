package com.caij.codehub.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.VolleyError;
import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.presenter.BasePresent;
import com.caij.codehub.ui.activity.RepositoryInfoActivity;
import com.caij.codehub.ui.adapter.RepositoryAdapter;
import com.caij.codehub.ui.listener.RepositoryListUi;

import java.util.List;

/**
 * Created by Caij on 2015/9/18.
 */
public abstract class RepositoriesFragment extends ListFragment<RepositoryAdapter> implements RepositoryListUi {

    protected Page mPage;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListView.setDivider(null);

        mPage = new Page();
    }

    @Override
    protected RepositoryAdapter createAdapter() {
        return new RepositoryAdapter(getContext(), null);
    }

    @Override
    public void onRefreshRepositoriesSuccess(List<Repository> repositories) {
        hideError();
        content.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);

        mPage.next();
        mListView.setNoMore(repositories.size() < mPage.getPageDataCount());

        mAdapter.setEntities(repositories);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Repository repository = (Repository) adapterView.getAdapter().getItem(i);
        Intent intent = RepositoryInfoActivity.newInstance(getActivity(),
                repository.getOwner().getLogin(), repository.getName());
        startActivity(intent);
    }


    @Override
    public void onLoadMoreRepositoriesSuccess(List<Repository> repositories) {

        mListView.onLoadMoreComplete();
        mPage.next();
        mListView.setNoMore(repositories.size() < mPage.getPageDataCount());

        mAdapter.addEntities(repositories);
        mAdapter.notifyDataSetChanged();
    }

}
