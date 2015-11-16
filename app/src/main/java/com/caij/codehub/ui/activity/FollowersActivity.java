package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.interactor.UserListInteractor;
import com.caij.codehub.present.LoadType;
import com.caij.codehub.present.UsersPresent;
import com.caij.lib.utils.SPUtils;

/**
 * Created by Caij on 2015/9/25.
 */
public class FollowersActivity extends UserListActivity{

    private String mUsername;
    private String mToken;
    private UsersPresent mUsersPresent;

    public static Intent newIntent(Activity activity, String username) {
        Intent intent = new Intent(activity, FollowersActivity.class);
        intent.putExtra(Constant.USER_NAME, username);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUsername = getIntent().getStringExtra(Constant.USER_NAME);
        mToken = SPUtils.getString(Constant.USER_TOKEN, "");
        setToolbarTitle(getString(R.string.follows));
        mUsersPresent = new UsersPresent(this);
        mUsersPresent.getFollowers(LoadType.FIRST, mToken, mUsername, mPage);
    }

    @Override
    public void onRefresh() {
        mUsersPresent.getFollowers(LoadType.REFRESH, mToken, mUsername, mPage.createRefreshPage());
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mUsersPresent.getFollowers(LoadType.FIRST, mToken, mUsername,mPage);
    }

    @Override
    public void onLoadMore() {
        mUsersPresent.getFollowers(LoadType.MORE, mToken, mUsername, mPage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUsersPresent.onDeath();
    }
}
