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
public class FollowingsFragment extends UsersFragment{

    private String mUsername;
    private String mToken;
    private UsersPresent mUsersPresent;

    public static FollowingsFragment newInstance(String username) {
        FollowingsFragment followingsFragment = new FollowingsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.USER_NAME, username);
        followingsFragment.setArguments(bundle);
        return followingsFragment;
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
        mUsersPresent.getFollowing(LoadType.FIRST, mToken, mUsername, mPage);
    }

    @Override
    public void onLoadMore() {
        mUsersPresent.getFollowing(LoadType.LOADMORE, mToken, mUsername, mPage);
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
    public void onDestroyView() {
        mUsersPresent.onDeath();
        super.onDestroyView();
    }
}
