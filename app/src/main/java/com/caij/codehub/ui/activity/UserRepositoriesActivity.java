package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.ui.fragment.UserRepositoriesFragment;


/**
 * Created by Caij on 2015/9/24.
 */
public class UserRepositoriesActivity extends BaseCodeHubToolBarActivity {


    public static Intent newIntent(Activity activity, String username) {
        Intent intent = new Intent(activity, UserRepositoriesActivity.class);
        intent.putExtra(Constant.USER_NAME, username);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String username = getIntent().getStringExtra(Constant.USER_NAME);
        getSupportActionBar().setTitle(username);
        Fragment fragment =  UserRepositoriesFragment.newInstance(username);
        fragment.setUserVisibleHint(true);
        getSupportFragmentManager().beginTransaction().add(R.id.content, fragment).commit();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_frame_layout;
    }

}
