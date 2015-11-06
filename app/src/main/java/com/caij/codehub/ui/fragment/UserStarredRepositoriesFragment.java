package com.caij.codehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.caij.codehub.Constant;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.presenter.RepositoryListPresenter;
import com.caij.lib.utils.CheckValueUtil;
import com.caij.lib.utils.SPUtils;


/**
 * Created by Caij on 2015/9/21.
 */
public class UserStarredRepositoriesFragment extends RepositoriesFragment {

    protected String mUsername;
    protected String mToken;
    private RepositoryListPresenter mRepositoryListPresenter;

    public static RepositoriesFragment newInstance(String username) {
        CheckValueUtil.check(username);
        Bundle bundle = new Bundle();
        bundle.putString(Constant.USER_NAME, username);
        RepositoriesFragment fragment = new UserStarredRepositoriesFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUsername = getArguments().getString(Constant.USER_NAME);
        mToken = SPUtils.getString(Constant.USER_TOKEN, "");
        mRepositoryListPresenter = PresenterFactory.newPresentInstance(RepositoryListPresenter.class);
    }

    @Override
    protected void onUserFirstVisible() {
        mPage.reset();
        mRepositoryListPresenter.getUserStarredRepositories(mUsername, mToken, mPage, this, mFirstLoadUiCallBack);
    }

    @Override
    public void onRefresh() {
        mRepositoryListPresenter.getUserStarredRepositories(mUsername, mToken, mPage.createRefreshPage(), this, mLoadRefreshUiCallBack);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mRepositoryListPresenter.getUserStarredRepositories(mUsername, mToken, mPage, this, mFirstLoadUiCallBack);
    }

    @Override
    public void onLoadMore() {
        mRepositoryListPresenter.getUserStarredRepositories(mUsername, mToken, mPage, this, mLoadMoreUiCallBack);
    }
}
