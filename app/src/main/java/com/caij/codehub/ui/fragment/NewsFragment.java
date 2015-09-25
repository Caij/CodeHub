package com.caij.codehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.caij.codehub.Constant;
import com.caij.codehub.bean.News;
import com.caij.codehub.bean.Page;
import com.caij.codehub.dagger.DaggerUtils;
import com.caij.codehub.presenter.BasePresent;
import com.caij.codehub.presenter.NewsPresenter;
import com.caij.codehub.ui.adapter.NewsAdapter;
import com.caij.codehub.ui.listener.NewsUi;
import com.caij.lib.utils.SPUtils;

import java.util.List;

/**
 * Created by Caij on 2015/9/24.
 */
public class NewsFragment extends ListFragment<NewsPresenter, NewsAdapter> implements NewsUi{

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getReceivedEvents("Caij", SPUtils.get(Constant.USER_TOKEN, ""),
                BasePresent.LoadType.FIRSTLOAD, new Page(5));
    }

    @Override
    protected NewsAdapter createAdapter() {
        return new NewsAdapter(null);
    }

    @Override
    protected void onUserFirstVisible() {

    }

    @Override
    public NewsPresenter getPresenter() {
        return DaggerUtils.getPresenterComponent().prvideNewsPresenter();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onGetNewsSuccess(List<News> newses) {

    }

    @Override
    public void onLoadMoreSuccess(List<News> newses) {

    }
}
