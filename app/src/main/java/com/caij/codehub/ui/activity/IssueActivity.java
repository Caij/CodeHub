package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;

import com.android.volley.VolleyError;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Comment;
import com.caij.codehub.bean.Issue;
import com.caij.codehub.presenter.Present;
import com.caij.codehub.presenter.CommentsPresent;
import com.caij.codehub.presenter.IssuePresent;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.ui.adapter.BaseAdapter;
import com.caij.codehub.ui.adapter.CommentAdapter;
import com.caij.codehub.ui.listener.CommentsUi;
import com.caij.codehub.ui.listener.IssueUi;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;

import java.util.List;

/**
 * Created by Caij on 2015/10/31.
 */
public class IssueActivity extends SwipeRefreshRecyclerViewActivity<Comment> implements IssueUi, CommentsUi {

    TextView tvIssueBody;
    private String mRepo;
    private String mIssueNumber;
    private CommentsPresent mCommentsPresent;
    private String mOwner;
    private TextView mIssueTitleTextView;
    private IssuePresent mIssuePresent;
    private String mIssueTitle;
    private String mIssueBody;

    public static Intent newIntent(Activity activity, String owner, String repo, String issueNumber, String issueTitle, String issueBody) {
        Intent intent = new Intent(activity, IssueActivity.class);
        intent.putExtra(Constant.REPO_NAME, repo);
        intent.putExtra(Constant.USER_NAME, owner);
        intent.putExtra(Constant.ISSUE_NUMBER, issueNumber);
        intent.putExtra(Constant.ISSUE_TITLE, issueTitle);
        intent.putExtra(Constant.ISSUE_BODY, issueBody);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoadMoreRecyclerView().setLoadMoreEnable(false);
        mRepo = getIntent().getStringExtra(Constant.REPO_NAME);
        mIssueNumber = getIntent().getStringExtra(Constant.ISSUE_NUMBER);
        mOwner = getIntent().getStringExtra(Constant.USER_NAME);
        mIssueTitle = getIntent().getStringExtra(Constant.ISSUE_TITLE);
        mIssueBody = getIntent().getStringExtra(Constant.ISSUE_BODY);

        setToolbarTitle(mRepo + "  #" + mIssueNumber);

//        mIssueTitleTextView = (TextView) findViewById(R.id.tv_issue_title);
//        tvIssueBody = (TextView) findViewById(R.id.tv_issue_body);
//        mIssueTitleTextView.setText(getString(R.string.issue) + ": " + issueTitle);
//        tvIssueBody.setText(issueBody);

        mCommentsPresent = PresenterFactory.newPresentInstance(CommentsPresent.class, CommentsUi.class, this);
        mCommentsPresent.getIssuesComments(Present.LoadType.FIRSTLOAD, mOwner, mRepo, mIssueNumber);
//        mIssuePresent = PresenterFactory.newPresentInstance(IssuePresent.class, IssueUi.class, this);
//        mIssuePresent.getIssue(mOwner, mRepo, mIssueNumber);
    }

    @Override
    protected BaseAdapter<Comment> createRecyclerViewAdapter() {
        return new CommentAdapter(null, this);
    }

    @Override
    protected LoadMoreRecyclerView.LayoutManager createRecyclerViewLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void onGetIssueSuccess(Issue issue) {
        mIssueTitleTextView.setText(issue.getTitle());
        tvIssueBody.setText(issue.getBody());
    }

    @Override
    public void onGetIssueError(VolleyError error) {

    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mCommentsPresent.getIssuesComments(Present.LoadType.FIRSTLOAD, mOwner, mRepo, mIssueNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_issue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.comment) {
            Intent intent = CommentActivity.newIntent(this, mOwner, mRepo, mIssueNumber);
            startActivityForResult(intent, Constant.ISSUE_COMMENT_REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Constant.ISSUE_COMMENT_REQUEST_CODE) {
                Comment comment = (Comment) data.getSerializableExtra(Constant.COMMENT);
                getRecyclerViewAdapter().addEntity(comment);
                getRecyclerViewAdapter().notifyDataSetChanged();
            }
        }

    }

    @Override
    public void onRefresh() {
        mCommentsPresent.getIssuesComments(Present.LoadType.REFRESH, mOwner, mRepo, mIssueNumber);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefreshSuccess(List<Comment> entities) {
        Comment comment = new Comment();
        comment.setId(- 1);
        comment.setIssue_url(mIssueTitle);
        comment.setBody(mIssueBody);
        entities.add(0, comment);
        super.onRefreshSuccess(entities);
    }

    @Override
    public void onFirstLoadSuccess(List<Comment> entities) {
        Comment comment = new Comment();
        comment.setId(- 1);
        comment.setIssue_url(mIssueTitle);
        comment.setBody(mIssueBody);
        entities.add(0, comment);
        super.onFirstLoadSuccess(entities);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
