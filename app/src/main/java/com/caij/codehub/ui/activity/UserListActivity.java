package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.caij.codehub.Constant;
import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.User;
import com.caij.codehub.dagger.DaggerUtils;
import com.caij.codehub.presenter.BasePresent;
import com.caij.codehub.presenter.UserListPresenter;
import com.caij.codehub.ui.adapter.UserAdapter;
import com.caij.codehub.ui.listener.UserListUi;
import com.caij.lib.utils.SPUtils;

import java.util.List;

/**
 * Created by Caij on 2015/9/24.
 */
public abstract class UserListActivity extends ListActivity<UserListPresenter, UserAdapter> implements UserListUi{


    Page mPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListView.setDividerHeight(2);
        mPage = new Page();

    }

    @Override
    protected UserAdapter createAdapter() {
        return new UserAdapter(this, null);
    }

    @Override
    public UserListPresenter getPresenter() {
        return DaggerUtils.getPresenterComponent().provideUserListPresenter();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        User user = (User) adapterView.getAdapter().getItem(i);
        Intent intent = UserInfoActivity.newIntent(this, user.getLogin());
        startActivity(intent);
    }



    @Override
    public void onGetUsersSuccess(List<User> users) {
        hideError();
        content.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);

        mPage.next();
        mListView.setNoMore(users.size() < mPage.getPageDataCount());

        mAdapter.setEntities(users);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreSuccess(List<User> users) {
        mListView.onLoadMoreComplete();
        mPage.next();
        mListView.setNoMore(users.size() < mPage.getPageDataCount());

        mAdapter.addEntities(users);
        mAdapter.notifyDataSetChanged();
    }

}
