package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.interactor.UserListInteractor;
import com.caij.codehub.present.LoadType;
import com.caij.codehub.present.UsersPresent;

/**
 * Created by Caij on 2015/9/25.
 */
public class FollowingActivity extends UserListActivity{

    private String mUsername;
    private String mToken;
    private UsersPresent mUsersPresent;

    public static Intent newIntent(Activity activity, String username) {
        Intent intent = new Intent(activity, FollowingActivity.class);
        intent.putExtra(Constant.USER_NAME, username);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUsername = getIntent().getStringExtra(Constant.USER_NAME);
        mToken = CodeHubPrefs.get().getToken();
        setToolbarTitle(getString(R.string.following));
        mUsersPresent = new UsersPresent(this);
        mUsersPresent.getFollowing(LoadType.FIRST, mToken, mUsername, mPage);
    }

    @Override
    public void onRefresh() {
        mUsersPresent.getFollowing(LoadType.REFRESH, mToken, mUsername, mPage.createRefreshPage());
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mUsersPresent.getFollowing(LoadType.FIRST, mToken, mUsername, mPage);
    }

    @Override
    public void onLoadMore() {
        mUsersPresent.getFollowing(LoadType.MORE, mToken, mUsername, mPage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUsersPresent.onDeath();
    }
}
