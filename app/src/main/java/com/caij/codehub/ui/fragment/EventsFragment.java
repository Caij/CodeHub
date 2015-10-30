package com.caij.codehub.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.VolleyError;
import com.caij.codehub.Constant;
import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.event.EventWrap;
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
public class EventsFragment extends ListFragment<NewsPresenter, NewsAdapter> implements NewsUi{

    Page mPage;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView.setDivider(null);
        mPage = new Page();
    }

    @Override
    protected NewsAdapter createAdapter() {
        return new NewsAdapter(getActivity(), null);
    }

    @Override
    protected void onUserFirstVisible() {
        mPresenter.getReceivedEvents("Caij", SPUtils.get(Constant.USER_TOKEN, ""),
                BasePresent.LoadType.FIRSTLOAD, mPage);
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
        mPage.reset();
        mPresenter.getReceivedEvents("Caij", SPUtils.get(Constant.USER_TOKEN, ""),
                BasePresent.LoadType.REFRESH, mPage);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getReceivedEvents("Caij", SPUtils.get(Constant.USER_TOKEN, ""),
                BasePresent.LoadType.LOADMOER, mPage);
    }

    @Override
    public void onGetNewsSuccess(final List<EventWrap> newses) {

        hideError();
        content.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);

        mPage.next();
        mListView.setNoMore(newses.size() < mPage.getPageDataCount());

        mAdapter.setEntities(newses);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreSuccess(final List<EventWrap> newses) {
        mListView.onLoadMoreComplete();
        mPage.next();
        mListView.setNoMore(newses.size() < mPage.getPageDataCount());
        mAdapter.addEntities(newses);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mPage.reset();
        mPresenter.getReceivedEvents("Caij", SPUtils.get(Constant.USER_TOKEN, ""),
                BasePresent.LoadType.REFRESH, mPage);
    }


    @Override
    public void showError(int type, VolleyError error) {
        super.showError(type, error);
        if (type == BasePresent.LoadType.LOADMOER) {
            mListView.onLoadMoreComplete();
        }
    }
}
