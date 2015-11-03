package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.caij.codehub.CodeHubApplication;
import com.caij.codehub.Constant;
import com.caij.codehub.presenter.Present;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.presenter.UserListPresenter;
import com.caij.codehub.ui.listener.UserListUi;

/**
 * Created by Caij on 2015/9/25.
 */
public class FollowingActivity extends UserListActivity{

    private String mUsername;
    private String mToken;
    private UserListPresenter mPresenter;

    public static Intent newIntent(Activity activity, String username) {
        Intent intent = new Intent(activity, FollowingActivity.class);
        intent.putExtra(Constant.USER_NAME, username);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUsername = getIntent().getStringExtra(Constant.USER_NAME);
        mToken = CodeHubApplication.getToken();
        setToolbarTitle("Following");
        mPresenter = PresenterFactory.newPresentInstance(UserListPresenter.class, UserListUi.class, this);
        mPresenter.getFollowing(mToken, mUsername, Present.LoadType.FIRSTLOAD, mPage);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getFollowing(mToken, mUsername, Present.LoadType.LOADMOER, mPage);
    }

    @Override
    public void onRefresh() {
        mPage.reset();
        mPresenter.getFollowing(mToken, mUsername, Present.LoadType.REFRESH, mPage);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mPage.reset();
        mPresenter.getFollowing(mToken, mUsername, Present.LoadType.FIRSTLOAD, mPage);
    }

}
