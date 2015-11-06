//package com.caij.codehub.ui.activity;
//
//import android.os.Bundle;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.view.View;
//import android.widget.AdapterView;
//
//import com.android.volley.VolleyError;
//import com.caij.codehub.R;
//import com.caij.codehub.bean.Entity;
//import com.caij.codehub.presenter.Present;
//import com.caij.codehub.ui.adapter.BaseAdapter;
//import com.caij.codehub.ui.listener.ListUi;
//import com.caij.codehub.widgets.LoadMoreListView;
//
//import java.util.List;
//
//import butterknife.Bind;
//
///**
// * Created by Caij on 2015/9/24.
// */
//public abstract class ListActivity<AP extends BaseAdapter<E>, E extends Entity> extends BaseCodeHubActivity implements LoadMoreListView.OnLoadMoreListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener , ListUi<E>{
//
//    @Bind(R.id.swipe_refresh_layout)
//    SwipeRefreshLayout mSwipeRefreshLayout;
//    protected AP mAdapter;
//    @Bind(R.id.list_view)
//    LoadMoreListView mListView;
//    @Override
//
//    protected int getContentLayoutId() {
//        return R.layout.list_view;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mAdapter = createAdapter();
//        mListView.setOnLoadMoreListener(this);
//        mListView.setAdapter(mAdapter);
//        mListView.setOnItemClickListener(this);
//        mSwipeRefreshLayout.setOnRefreshListener(this);
//        mSwipeRefreshLayout.setColorSchemeColors(
//                getResources().getColor(R.color.gplus_color_1),
//                getResources().getColor(R.color.gplus_color_2),
//                getResources().getColor(R.color.gplus_color_3),
//                getResources().getColor(R.color.gplus_color_4));
//
//        mContentContainer.setVisibility(View.GONE);
//    }
//
//    protected abstract AP createAdapter();
//
//    public LoadMoreListView getListView() {
//        return mListView;
//    }
//
//
//    @Override
//    public void onFirstLoadSuccess(List<E> entities) {
//        showContentContainer();
//        mAdapter.setEntities(entities);
//        mAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onRefreshSuccess(List<E> entities) {
//        mSwipeRefreshLayout.setRefreshing(false);
//        mAdapter.setEntities(entities);
//        mAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onLoadMoreSuccess(List<E>  entities) {
//        mListView.onLoadMoreComplete();
//        mAdapter.addEntities(entities);
//        mAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onRefreshError(VolleyError error) {
//        mSwipeRefreshLayout.setRefreshing(false);
//        processVolleyError(error);
//    }
//
//    @Override
//    public void onLoadMoreError(VolleyError error) {
//        mListView.onLoadMoreComplete();
//        processVolleyError(error);
//    }
//
//    @Override
//    public void onFirstLoadError(VolleyError error) {
//        showError();
//    }
//
//    @Override
//    public void onComnLoading(int loadType) {
//        if (loadType == Present.LoadType.FIRSTLOAD) {
//            showLoading();
//        }else if (loadType == Present.LoadType.REFRESH) {
//        }else if (loadType == Present.LoadType.LOADMOER) {
//        }
//    }
//
//    @Override
//    public void onLoaded(int loadType) {
//        if (loadType == Present.LoadType.FIRSTLOAD) {
//            hideLoading();
//        }else if (loadType == Present.LoadType.REFRESH) {
//        }else if (loadType == Present.LoadType.LOADMOER) {
//        }
//    }
//}
