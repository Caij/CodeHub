package com.caij.codehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.Constant;
import com.caij.codehub.present.LoadType;
import com.caij.codehub.present.UsersPresent;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/18.
 */
public class FollowersFragment extends UsersFragment{


    private String mUsername;
    private String mToken;
    private UsersPresent mUsersPresent;

    public static FollowersFragment newInstance(String username) {
        FollowersFragment followersFragment = new FollowersFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.USER_NAME, username);
        followersFragment.setArguments(bundle);
        return followersFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUsername = getArguments().getString(Constant.USER_NAME);
        mToken = CodeHubPrefs.get().getToken();
        mUsersPresent = new UsersPresent(this);
    }

    @Override
    protected void onUserFirstVisible() {
        mUsersPresent.getFollowers(LoadType.FIRST, mToken, mUsername, mPage);
    }

    @Override
    public void onLoadMore() {
        mUsersPresent.getFollowers(LoadType.LOADMORE, mToken, mUsername, mPage);
    }

    @Override
    public void onRefresh() {
        mUsersPresent.getFollowers(LoadType.REFRESH, mToken, mUsername, mPage.createRefreshPage());
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mUsersPresent.getFollowers(LoadType.FIRST, mToken, mUsername, mPage);
    }

    @Override
    public void onDestroyView() {
        mUsersPresent.onDeath();
        super.onDestroyView();
    }
}
