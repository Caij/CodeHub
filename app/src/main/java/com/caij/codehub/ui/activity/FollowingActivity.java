package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.caij.codehub.Constant;
import com.caij.codehub.presenter.BasePresent;
import com.caij.lib.utils.SPUtils;

/**
 * Created by Caij on 2015/9/25.
 */
public class FollowingActivity extends UserListActivity{

    private String mUsername;
    private String mToken;

    public static Intent newIntent(Activity activity, String username) {
        Intent intent = new Intent(activity, FollowingActivity.class);
        intent.putExtra(Constant.USER_NAME, username);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUsername = getIntent().getStringExtra(Constant.USER_NAME);
        mToken = SPUtils.get(Constant.USER_TOKEN, "");
        setToolbarTitle("Following");
        mPresenter.getFollowing(mToken, mUsername, BasePresent.LoadType.FIRSTLOAD, mPage);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getFollowing(mToken, mUsername, BasePresent.LoadType.LOADMOER, mPage);
    }

    @Override
    public void onRefresh() {
        mPage.reset();
        mPresenter.getFollowing(mToken, mUsername, BasePresent.LoadType.REFRESH, mPage);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mPage.reset();
        mPresenter.getFollowing(mToken, mUsername, BasePresent.LoadType.FIRSTLOAD, mPage);
    }

}
