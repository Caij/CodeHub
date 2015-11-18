package com.caij.codehub.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.caij.codehub.Constant;
import com.caij.codehub.bean.Issue;
import com.caij.codehub.bean.Page;
import com.caij.codehub.present.RepoIssuesPresent;
import com.caij.codehub.present.LoadType;
import com.caij.codehub.ui.activity.IssueInfoActivity;
import com.caij.codehub.ui.adapter.BaseAdapter;
import com.caij.codehub.ui.adapter.IssueAdapter;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;

import java.util.List;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/18.
 */
public class RepoIssuesFragment extends SwipeRefreshRecyclerViewFragment<Issue>{


    private Page mPage;
    private RepoIssuesPresent mRepoIssuesPresent;
    private String mOwner;
    private String mRepo;

    public static RepoIssuesFragment newInstance(String owner, String repo) {
        RepoIssuesFragment fragment = new RepoIssuesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.USER_NAME, owner);
        bundle.putString(Constant.REPO_NAME, repo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        mOwner = bundle.getString(Constant.USER_NAME);
        mRepo = bundle.getString(Constant.REPO_NAME);
        mPage = new Page();
        mRepoIssuesPresent = new RepoIssuesPresent(this);
    }

    @Override
    protected void onUserFirstVisible() {
        mRepoIssuesPresent.getIssueList(LoadType.FIRST, mOwner, mRepo, mPage);
    }

    @Override
    protected BaseAdapter<Issue> createRecyclerViewAdapter() {
        return new IssueAdapter(getActivity());
    }

    @Override
    protected LoadMoreRecyclerView.LayoutManager createRecyclerViewLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void onFirstLoadSuccess(List<Issue> entities) {
        super.onFirstLoadSuccess(entities);
        mPage.reset();
        mPage.next();
        getLoadMoreRecyclerView().setState(entities.size() < mPage.getPageDataCount() ? LoadMoreRecyclerView.STATE_NO_MORE : LoadMoreRecyclerView.STATE_NORMAL);
    }

    @Override
    public void onRefreshSuccess(List<Issue> entities) {
        super.onRefreshSuccess(entities);
        mPage.reset();
        mPage.next();
        getLoadMoreRecyclerView().setState(entities.size() < mPage.getPageDataCount() ? LoadMoreRecyclerView.STATE_NO_MORE : LoadMoreRecyclerView.STATE_NORMAL);
    }

    @Override
    public void onLoadMoreSuccess(List<Issue> entities) {
        super.onLoadMoreSuccess(entities);
        mPage.next();
        getLoadMoreRecyclerView().setState(entities.size() < mPage.getPageDataCount() ? LoadMoreRecyclerView.STATE_NO_MORE : LoadMoreRecyclerView.STATE_NORMAL);
    }

    @Override
    public void onRefresh() {
        mRepoIssuesPresent.getIssueList(LoadType.REFRESH, mOwner, mRepo, mPage.createRefreshPage());
    }

    @Override
    public void onLoadMore() {
        mRepoIssuesPresent.getIssueList(LoadType.MORE, mOwner, mRepo, mPage);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mRepoIssuesPresent.getIssueList(LoadType.FIRST, mOwner, mRepo, mPage);
    }

    @Override
    public void onItemClick(View view, int position) {
        Issue issue = getRecyclerViewAdapter().getItem(position);
        Intent intent = IssueInfoActivity.newIntent(getActivity(), mOwner, mRepo, String.valueOf(issue.getNumber()), issue.getTitle(), issue.getBody());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRepoIssuesPresent.onDeath();
    }
}
