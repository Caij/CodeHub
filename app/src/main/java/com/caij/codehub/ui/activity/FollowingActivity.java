package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;

import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.ui.fragment.FollowingsFragment;

/**
 * Created by Caij on 2015/9/25.
 */
public class FollowingActivity extends BaseCodeHubToolBarActivity{

    public static Intent newIntent(Activity activity, String username) {
        Intent intent = new Intent(activity, FollowingActivity.class);
        intent.putExtra(Constant.USER_NAME, username);
        return intent;
    }

    @Override
    protected void handleIntent(Intent intent) {
        setTitle(getString(R.string.following));
        FollowingsFragment fragment = FollowingsFragment.newInstance(getIntent().getStringExtra(Constant.USER_NAME));
        fragment.setUserVisibleHint(true);
        getSupportFragmentManager().beginTransaction()
        .replace(R.id.base_code_hub_container, fragment)
                .commit();
    }

    @Override
    protected int getAttachLayoutId() {
        return 0;
    }

}
