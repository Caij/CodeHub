//package com.caij.codehub.ui.fragment;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.View;
//
//import com.android.volley.VolleyError;
//import com.caij.codehub.Constant;
//import com.caij.codehub.presenter.BasePresent;
//import com.caij.codehub.presenter.PresenterFactory;
//import com.caij.codehub.presenter.RepositoryListPresenter;
//import com.caij.codehub.ui.listener.RepositoryListUi;
//import com.caij.lib.utils.CheckValueUtil;
//
///**
// * Created by Caij on 2015/9/21.
// */
//public class SearchRepositoriesFragment extends RepositoriesFragment{
//
//    private String mRepoSearchQ;
//    private String mRepoSort;
//    private String mOrder;
//    private RepositoryListPresenter mPresenter;
//
//    public static RepositoriesFragment newInstance(String q, String sort, String order) {
//        Bundle bundle = new Bundle();
//        CheckValueUtil.check(sort);
//        bundle.putString(Constant.REPO_SEARCH_Q, q);
//        bundle.putString(Constant.REPO_SORT, sort);
//        bundle.putString(Constant.ORDER, order);
//        RepositoriesFragment fragment = new SearchRepositoriesFragment();
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        mRepoSearchQ = getArguments().getString(Constant.REPO_SEARCH_Q);
//        mRepoSort = getArguments().getString(Constant.REPO_SORT);
//        mOrder = getArguments().getString(Constant.ORDER);
//        mListView.setCanLoadMore(false);
//        mPresenter = PresenterFactory.newPresentInstance(RepositoryListPresenter.class, RepositoryListUi.class, this);
//    }
//
//    @Override
//    protected void onUserFirstVisible() {
//        mPresenter.getSearchRepository(BasePresent.LoadType.FIRSTLOAD, mRepoSearchQ, mRepoSort, mOrder, mPage);
//    }
//
//    @Override
//    public void onRefresh() {
//        mPage.reset();
//        mPresenter.getSearchRepository(BasePresent.LoadType.REFRESH, mRepoSearchQ, mRepoSort, mOrder, mPage);
//    }
//
//    @Override
//    public void onReFreshBtnClick(View view) {
//        super.onReFreshBtnClick(view);
//        mPresenter.getSearchRepository(BasePresent.LoadType.FIRSTLOAD, mRepoSearchQ, mRepoSort, mOrder, mPage);
//    }
//
//    @Override
//    public void onLoadMore() {
//
//    }
//
//    @Override
//    public void showError(int type, VolleyError error) {
//        super.showError(type, error);
//        if (type == BasePresent.LoadType.REFRESH) {
//            mPage.scrollBack(); //用于刷新的时候重置page刷新错误，导致下拉index出错。
//        }
//    }
//}
