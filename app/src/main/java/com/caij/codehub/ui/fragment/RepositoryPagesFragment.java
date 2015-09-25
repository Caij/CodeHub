package com.caij.codehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caij.codehub.API;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.lib.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/9/17.
 */
public class RepositoryPagesFragment extends BaseFragment {

    @Bind(R.id.tabs)
    TabLayout mTabs;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;

    private static final String[] FRAGMENT_TITLES= {"Owned", "Starred", "Trending"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repository_viewpager, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<BaseFragment> fragments = new ArrayList<>();
        String username = SPUtils.get(Constant.USER_NAME, "");
        fragments.add(UserRepositoriesFragment.newInstance(username));
        fragments.add(UserStarredRepositoriesFragment.newInstance(username));
//        fragments.add(SearchRepositoriesFragment.newInstance("language:java", "stars", "desc"));
        fragments.add(TrendingRepositoriesFragment.newInstance());
        RepositoryPageAdapter adapter = new RepositoryPageAdapter(getFragmentManager(), fragments);
        mViewpager.setOffscreenPageLimit(fragments.size());
        mViewpager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewpager);
    }

    private static class RepositoryPageAdapter extends FragmentPagerAdapter {

        private List<BaseFragment> mFragments;

        public RepositoryPageAdapter(FragmentManager fm, List<BaseFragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return FRAGMENT_TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return FRAGMENT_TITLES[position];
        }
    }

}
