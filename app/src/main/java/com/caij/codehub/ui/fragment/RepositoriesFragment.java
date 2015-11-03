package com.caij.codehub.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.VolleyError;
import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.ui.activity.RepositoryInfoActivity;
import com.caij.codehub.ui.adapter.RepositoryAdapter;
import com.caij.codehub.ui.listener.RepositoryListUi;

import java.util.List;

/**
 * Created by Caij on 2015/9/18.
 */
public abstract class RepositoriesFragment extends ListFragment<RepositoryAdapter, Repository> implements RepositoryListUi {

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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Repository repository = (Repository) adapterView.getAdapter().getItem(i);
        Intent intent = RepositoryInfoActivity.newInstance(getActivity(),
                repository.getOwner().getLogin(), repository.getName());
        startActivity(intent);
    }

    @Override
    public void onFirstLoadSuccess(List<Repository> repositories) {
        super.onFirstLoadSuccess(repositories);
        mPage.next();
        mListView.setNoMore(repositories.size() < mPage.getPageDataCount());
    }

    @Override
    public void onRefreshSuccess(List<Repository> repositories) {
        super.onRefreshSuccess(repositories);
        mPage.next();
        mListView.setNoMore(repositories.size() < mPage.getPageDataCount());
    }

    @Override
    public void onLoadMoreSuccess(List<Repository> repositories) {
        super.onLoadMoreSuccess(repositories);
        mPage.next();
        mListView.setNoMore(repositories.size() < mPage.getPageDataCount());
    }

    @Override
    public void onRefreshError(VolleyError error) {
        super.onRefreshError(error);
        mPage.scrollBack();
    }
}
