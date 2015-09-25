package com.caij.codehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.caij.codehub.Constant;
import com.caij.codehub.presenter.BasePresent;
import com.caij.lib.utils.CheckValueUtil;

/**
 * Created by Caij on 2015/9/21.
 */
public class TrendingRepositoriesFragment extends RepositoriesFragment{

    public static RepositoriesFragment newInstance() {
        RepositoriesFragment fragment = new TrendingRepositoriesFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView.setCanLoadMore(false);
    }

    @Override
    protected void onUserFirstVisible() {
        mPresenter.getTrendingRepository(BasePresent.LoadType.FIRSTLOAD, null, null);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.getTrendingRepository(BasePresent.LoadType.REFRESH, null, null);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mPresenter.getTrendingRepository(BasePresent.LoadType.REFRESH, null, null);
    }
}
