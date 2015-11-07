package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Issue;
import com.caij.codehub.bean.Page;
import com.caij.codehub.presenter.IssueListPresent;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.ui.adapter.BaseAdapter;
import com.caij.codehub.ui.adapter.IssueAdapter;
import com.caij.codehub.ui.intf.IssueListUi;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;

import java.util.List;

/**
 * Created by Caij on 2015/11/3.
 */
public class IssueListActivity extends SwipeRefreshRecyclerViewActivity<Issue> implements IssueListUi {

    private IssueListPresent mIssueListPresent;
    private String mOwner;
    private String mRepo;
    private Page mPage;

    public static Intent newIntent(Activity activity, String owner, String repo) {
        Intent intent = new Intent(activity, IssueListActivity.class);
        intent.putExtra(Constant.USER_NAME, owner);
        intent.putExtra(Constant.REPO_NAME, repo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(getString(R.string.issue));
        mOwner = getIntent().getStringExtra(Constant.USER_NAME);
        mRepo = getIntent().getStringExtra(Constant.REPO_NAME);
        mPage = new Page();
        mIssueListPresent = PresenterFactory.newPresentInstance(IssueListPresent.class);
        mIssueListPresent.getIssueList(mOwner, mRepo, mPage, this, mFirstLoadUiCallBack);
    }

    @Override
    protected BaseAdapter<Issue> createRecyclerViewAdapter() {
        return new IssueAdapter(this, null);
    }

    @Override
    protected LoadMoreRecyclerView.LayoutManager createRecyclerViewLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void onFirstLoadSuccess(List<Issue> entities) {
        super.onFirstLoadSuccess(entities);
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
        mIssueListPresent.getIssueList(mOwner, mRepo, mPage.createRefreshPage(), this, mLoadRefreshUiCallBack);
    }

    @Override
    public void onLoadMore() {
        mIssueListPresent.getIssueList(mOwner, mRepo, mPage, this, mLoadMoreUiCallBack);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mIssueListPresent.getIssueList(mOwner, mRepo, mPage, this, mFirstLoadUiCallBack);
    }

    @Override
    public void onItemClick(View view, int position) {
        Issue issue = getRecyclerViewAdapter().getItem(position);
        Intent intent = IssueActivity.newIntent(this, mOwner, mRepo, String.valueOf(issue.getNumber()), issue.getTitle(), issue.getBody());
        startActivity(intent);
    }
}
