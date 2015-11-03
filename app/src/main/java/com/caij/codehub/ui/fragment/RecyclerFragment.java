//package com.caij.codehub.ui.fragment;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//
//import com.caij.codehub.R;
//import com.caij.codehub.presenter.Present;
//import com.caij.codehub.ui.adapter.BaseAdapter;
//import com.caij.codehub.ui.adapter.RecycleViewItemClickListener;
//import com.caij.codehub.ui.adapter.RepositoryAdapter;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//
///**
// * Created by Caij on 2015/9/23.
// */
//public abstract class RecyclerFragment<P extends Present, AP extends BaseAdapter> extends LazyFragment<P> implements SwipeRefreshLayout.OnRefreshListener, RecycleViewItemClickListener {
//
//    @Bind(R.id.recycle_view)
//    RecyclerView mRecycleView;
//    @Bind(R.id.swipe_refresh_layout)
//    SwipeRefreshLayout mSwipeRefreshLayout;
//    protected AP mAdapter;
//
//    @Override
//    protected int getContentLayoutId() {
//        return R.layout.fragment_recycler_view;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
//
//        mRecycleView.setLayoutManager(layoutManager);
//        mAdapter = createAdapter();
//        mAdapter.setOnRecycleViewItemClickListener(this);
//        mRecycleView.setAdapter(mAdapter);
//
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
//}
