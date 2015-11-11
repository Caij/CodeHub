package com.caij.codehub.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.User;
import com.caij.codehub.ui.adapter.BaseAdapter;
import com.caij.codehub.ui.adapter.UserAdapter;
import com.caij.codehub.ui.callback.ListUi;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;


import java.util.List;

/**
 * Created by Caij on 2015/9/24.
 */
public abstract class UserListActivity extends SwipeRefreshRecyclerViewActivity<User> implements ListUi<User> {

    Page mPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = new Page();
    }

    @Override
    protected LoadMoreRecyclerView.LayoutManager createRecyclerViewLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    protected BaseAdapter<User> createRecyclerViewAdapter() {
        return new UserAdapter(this, null);
    }

    @Override
    public void onItemClick(View view, int position) {
        User user =getRecyclerViewAdapter().getItem(position);
        Intent intent = UserInfoActivity.newIntent(this, user.getLogin());
        startActivity(intent);
    }

    @Override
    public void onFirstLoadSuccess(List<User> users) {
        super.onFirstLoadSuccess(users);
        mPage.next();
        getLoadMoreRecyclerView().setState(users.size() < mPage.getPageDataCount() ? LoadMoreRecyclerView.STATE_NO_MORE : LoadMoreRecyclerView.STATE_NORMAL);
    }

    @Override
    public void onRefreshSuccess(List<User> users) {
        super.onRefreshSuccess(users);
        mPage.reset();
        mPage.next();
        getLoadMoreRecyclerView().setState(users.size() < mPage.getPageDataCount() ? LoadMoreRecyclerView.STATE_NO_MORE : LoadMoreRecyclerView.STATE_NORMAL);
    }

    @Override
    public void onLoadMoreSuccess(List<User> users) {
        super.onLoadMoreSuccess(users);
        mPage.next();
        getLoadMoreRecyclerView().setState(users.size() < mPage.getPageDataCount() ? LoadMoreRecyclerView.STATE_NO_MORE : LoadMoreRecyclerView.STATE_NORMAL);
    }

}
