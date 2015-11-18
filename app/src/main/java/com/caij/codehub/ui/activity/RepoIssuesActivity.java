package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;

import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.ui.fragment.RepoIssuesFragment;

/**
 * Created by Caij on 2015/11/3.
 */
public class RepoIssuesActivity extends BaseCodeHubToolBarActivity {

    public static Intent newIntent(Activity activity, String owner, String repo) {
        Intent intent = new Intent(activity, RepoIssuesActivity.class);
        intent.putExtra(Constant.USER_NAME, owner);
        intent.putExtra(Constant.REPO_NAME, repo);
        return intent;
    }

    @Override
    protected int getAttachLayoutId() {
        return 0;
    }

    @Override
    protected void handleIntent(Intent intent) {
        setTitle(getString(R.string.issue));
        String owner = getIntent().getStringExtra(Constant.USER_NAME);
        String repo = getIntent().getStringExtra(Constant.REPO_NAME);
        RepoIssuesFragment fragment = RepoIssuesFragment.newInstance(owner, repo);
        fragment.setUserVisibleHint(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.base_code_hub_container, fragment).commit();
    }

}
