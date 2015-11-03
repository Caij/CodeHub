package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.VolleyError;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Issue;
import com.caij.codehub.bean.Page;
import com.caij.codehub.presenter.Present;
import com.caij.codehub.presenter.IssueListPresent;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.ui.adapter.IssueAdapter;
import com.caij.codehub.ui.listener.IssueListUi;

import java.util.List;

/**
 * Created by Caij on 2015/11/3.
 */
public class IssueListActivity extends ListActivity<IssueAdapter, Issue> implements IssueListUi {

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
        getListView().setDivider(null);
        mOwner = getIntent().getStringExtra(Constant.USER_NAME);
        mRepo = getIntent().getStringExtra(Constant.REPO_NAME);
        mPage = new Page();
        mIssueListPresent = PresenterFactory.newPresentInstance(IssueListPresent.class, IssueListUi.class, this);
        mIssueListPresent.getIssueList(Present.LoadType.FIRSTLOAD, mOwner, mRepo, mPage);
    }

    @Override
    protected IssueAdapter createAdapter() {
        return new IssueAdapter(this, null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Issue issue = (Issue) parent.getAdapter().getItem(position);
        Intent intent = IssueActivity.newIntent(this, mOwner, mRepo, String.valueOf(issue.getNumber()), issue.getTitle(), issue.getBody());
        startActivity(intent);
    }

    @Override
    public void onFirstLoadSuccess(List<Issue> entities) {
        super.onFirstLoadSuccess(entities);
        mPage.next();
    }

    @Override
    public void onRefreshSuccess(List<Issue> entities) {
        super.onRefreshSuccess(entities);
        mPage.next();
    }

    @Override
    public void onLoadMoreSuccess(List<Issue> entities) {
        super.onLoadMoreSuccess(entities);
        mPage.next();
    }

    @Override
    public void onRefreshError(VolleyError error) {
        super.onRefreshError(error);
        mPage.scrollBack();
    }

    @Override
    public void onLoadMore() {
        mIssueListPresent.getIssueList(Present.LoadType.LOADMOER, mOwner, mRepo, mPage);
    }

    @Override
    public void onRefresh() {
        mPage.reset();
        mIssueListPresent.getIssueList(Present.LoadType.REFRESH, mOwner, mRepo, mPage);
    }
}
