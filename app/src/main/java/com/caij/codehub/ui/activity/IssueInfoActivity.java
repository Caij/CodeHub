package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Comment;
import com.caij.codehub.interactor.CommentsInteractor;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.present.CommentsPresent;
import com.caij.codehub.ui.adapter.BaseAdapter;
import com.caij.codehub.ui.adapter.CommentAdapter;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;


/**
 * Created by Caij on 2015/10/31.
 */
public class IssueInfoActivity extends SwipeRefreshRecyclerViewActivity<Comment> {

    private String mRepo;
    private String mIssueNumber;
    private String mOwner;
    private CommentsPresent mCommentsPresent;

    public static Intent newIntent(Activity activity, String owner, String repo, String issueNumber, String issueTitle, String issueBody) {
        Intent intent = new Intent(activity, IssueInfoActivity.class);
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

        setToolbarTitle(mRepo + "  #" + mIssueNumber);
        mCommentsPresent = new CommentsPresent(this);
        mCommentsPresent.getIssuesComments(mOwner, mRepo, mIssueNumber);
    }

    @Override
    protected BaseAdapter<Comment> createRecyclerViewAdapter() {
        String issueTitle = getIntent().getStringExtra(Constant.ISSUE_TITLE);
        String issueBody = getIntent().getStringExtra(Constant.ISSUE_BODY);
        View issueContentHeadView = getLayoutInflater().inflate(R.layout.item_issue_head, null, false);
        CommentAdapter adapter = new CommentAdapter(this);
        TextView tvIssueTitle = (TextView) issueContentHeadView.findViewById(R.id.tv_issue_title);
        TextView tvIssueBody = (TextView) issueContentHeadView.findViewById(R.id.tv_issue_body);
        tvIssueTitle.setText(issueTitle);
        tvIssueBody.setText(issueBody);
        adapter.addIssueContentHeadView(issueContentHeadView);
        return adapter;
    }

    @Override
    protected LoadMoreRecyclerView.LayoutManager createRecyclerViewLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mCommentsPresent.getIssuesComments(mOwner, mRepo, mIssueNumber);
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
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClick(View view, int position) {

    }

}
