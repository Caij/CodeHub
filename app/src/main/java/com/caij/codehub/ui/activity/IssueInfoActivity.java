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
import com.caij.codehub.ui.fragment.IssueInfoFragment;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;


/**
 * Created by Caij on 2015/10/31.
 */
public class IssueInfoActivity extends BaseCodeHubToolBarActivity {

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
    protected void handleIntent(Intent intent) {
        String repoName = intent.getStringExtra(Constant.REPO_NAME);
        String issueNumber = intent.getStringExtra(Constant.ISSUE_NUMBER);

        setTitle(repoName + "  #" + issueNumber);

        IssueInfoFragment fragment = IssueInfoFragment.newInstance(intent.getStringExtra(Constant.USER_NAME), repoName,
                issueNumber, intent.getStringExtra(Constant.ISSUE_TITLE), intent.getStringExtra(Constant.ISSUE_BODY));
        fragment.setUserVisibleHint(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.base_code_hub_container, fragment).commit();
    }

    @Override
    protected int getAttachLayoutId() {
        return 0;
    }

}
