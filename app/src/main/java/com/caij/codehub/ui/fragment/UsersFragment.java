package com.caij.codehub.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.User;
import com.caij.codehub.ui.activity.PictureReviewActivity;
import com.caij.codehub.ui.activity.UserInfoActivity;
import com.caij.codehub.ui.adapter.BaseAdapter;
import com.caij.codehub.ui.adapter.UserAdapter;
import com.caij.codehub.utils.AvatarUrlUtil;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;

import java.util.List;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/18.
 */
public abstract class UsersFragment extends SwipeRefreshRecyclerViewFragment<User>{

    Page mPage;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPage = new Page();
    }

    @Override
    protected LoadMoreRecyclerView.LayoutManager createRecyclerViewLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected BaseAdapter<User> createRecyclerViewAdapter() {
        return new UserAdapter(getActivity());
    }

    @Override
    public void onItemClick(View view, int position) {
        User user =getRecyclerViewAdapter().getItem(position);
        Intent intent = UserInfoActivity.newIntent(getActivity(), user.getLogin(), user.getAvatar_url());
        ActivityOptionsCompat optionsCompat
                = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(), view, getString(R.string.user_info));
        ActivityCompat.startActivity(getActivity(), intent,
                optionsCompat.toBundle());
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
