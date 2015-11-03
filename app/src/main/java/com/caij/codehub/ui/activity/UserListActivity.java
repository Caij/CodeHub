package com.caij.codehub.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.VolleyError;
import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.User;
import com.caij.codehub.ui.adapter.UserAdapter;
import com.caij.codehub.ui.listener.UserListUi;

import java.util.List;

/**
 * Created by Caij on 2015/9/24.
 */
public abstract class UserListActivity extends ListActivity<UserAdapter, User> implements UserListUi{

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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        User user = (User) adapterView.getAdapter().getItem(i);
        Intent intent = UserInfoActivity.newIntent(this, user.getLogin());
        startActivity(intent);
    }

    @Override
    public void onFirstLoadSuccess(List<User> users) {
        super.onFirstLoadSuccess(users);
        mPage.next();
        mListView.setNoMore(users.size() < mPage.getPageDataCount());
    }

    @Override
    public void onRefreshSuccess(List<User> users) {
        super.onRefreshSuccess(users);
        mPage.next();
        mListView.setNoMore(users.size() < mPage.getPageDataCount());
    }

    @Override
    public void onLoadMoreSuccess(List<User> users) {
        super.onLoadMoreSuccess(users);
        mPage.next();
        mListView.setNoMore(users.size() < mPage.getPageDataCount());
    }

    @Override
    public void onRefreshError(VolleyError error) {
        super.onRefreshError(error);
        mPage.scrollBack();
    }

}
