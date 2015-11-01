package com.caij.codehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.volley.VolleyError;
import com.caij.codehub.Constant;
import com.caij.codehub.presenter.BasePresent;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.presenter.RepositoryListPresenter;
import com.caij.codehub.ui.listener.RepositoryListUi;
import com.caij.lib.utils.CheckValueUtil;
import com.caij.lib.utils.SPUtils;


/**
 * Created by Caij on 2015/9/21.
 */
public class UserStarredRepositoriesFragment extends RepositoriesFragment {

    private RepositoryListPresenter mPresenter;

    public static RepositoriesFragment newInstance(String username) {
        CheckValueUtil.check(username);
        Bundle bundle = new Bundle();
        bundle.putString(Constant.USER_NAME, username);
        RepositoriesFragment fragment = new UserStarredRepositoriesFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    protected String mUsername;
    protected String mToken;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUsername = getArguments().getString(Constant.USER_NAME);
        mToken = SPUtils.get(Constant.USER_TOKEN, "");
        mPresenter = PresenterFactory.newPresentInstance(RepositoryListPresenter.class, RepositoryListUi.class, this);
    }

    @Override
    protected void onUserFirstVisible() {
        mPage.reset();
        mPresenter.getUserStarredRepositories(BasePresent.LoadType.FIRSTLOAD, mUsername, mToken, mPage);
    }

    @Override
    public void onRefresh() {
        mPage.reset();
        mPresenter.getUserStarredRepositories(BasePresent.LoadType.REFRESH, mUsername, mToken, mPage);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getUserStarredRepositories(BasePresent.LoadType.LOADMOER, mUsername, mToken, mPage);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mPresenter.getUserStarredRepositories(BasePresent.LoadType.FIRSTLOAD, mUsername, mToken, mPage);
    }


    @Override
    public void showError(int type, VolleyError error) {
        super.showError(type, error);
        if (type == BasePresent.LoadType.REFRESH) {
            mPage.scrollBack(); //用于刷新的时候重置page刷新错误，导致下拉index出错。
        }
        if (type == BasePresent.LoadType.LOADMOER) {
            mListView.onLoadMoreComplete();
        }
    }
}
