package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Comment;
import com.caij.codehub.bean.Issue;
import com.caij.codehub.presenter.CommentsPresent;
import com.caij.codehub.presenter.IssuePresent;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.ui.adapter.CommentAdapter;
import com.caij.codehub.ui.listener.CommentsUi;
import com.caij.codehub.ui.listener.IssueUi;
import com.caij.codehub.widgets.LoadMoreListView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Caij on 2015/10/31.
 */
public class IssueActivity extends BaseCodeHubActivity implements IssueUi, CommentsUi {

    TextView tvIssueTitle;
    @Bind(R.id.list_view)
    LoadMoreListView listView;
    TextView tvIssueBody;
    private String repoOwner;
    private String repo;
    private String issueNumber;
    private IssuePresent issuePresent;
    private CommentsPresent commentsPresent;
    private CommentAdapter adapter;

    public static Intent newIntent(Activity activity, Issue issue, String repo, String issueNumber) {
        Intent intent = new Intent(activity, IssueActivity.class);
        intent.putExtra(Constant.ISSUE, issue);
        intent.putExtra(Constant.REPO_NAME, repo);
        intent.putExtra(Constant.ISSUE_NUMBER, issueNumber);
        return intent;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_issue;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content.setVisibility(View.GONE);

        repo = getIntent().getStringExtra(Constant.REPO_NAME);
        issueNumber = getIntent().getStringExtra(Constant.ISSUE_NUMBER);

        setToolbarTitle(repo + "  #" + issueNumber);

        Issue issue = (Issue) getIntent().getSerializableExtra(Constant.ISSUE);

        adapter = new CommentAdapter(null, this);
        listView.setAdapter(adapter);
        listView.setDivider(null);
        listView.setCanLoadMore(false);

        View view = View.inflate(this, R.layout.item_issue_head, null);
        tvIssueTitle = (TextView) view.findViewById(R.id.tv_issue_title);
        tvIssueBody = (TextView) view.findViewById(R.id.tv_issue_body);
        tvIssueTitle.setText(issue.getTitle());
        tvIssueBody.setText(issue.getBody());
        listView.addHeaderView(view);

        commentsPresent = PresenterFactory.newPresentInstance(CommentsPresent.class, CommentsUi.class, this);
        commentsPresent.getIssuesComments(repo, issueNumber);
    }

    @Override
    public void onGetIssueSuccess(Issue issue) {
        tvIssueTitle.setText(issue.getTitle());
        tvIssueBody.setText(issue.getBody());
    }

    @Override
    public void onGetCommentsSuccess(List<Comment> comments) {
        content.setVisibility(View.VISIBLE);
        adapter.setEntities(comments);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        commentsPresent.getIssuesComments(repo, issueNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_issue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
