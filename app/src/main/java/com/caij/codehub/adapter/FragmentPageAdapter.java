package com.caij.codehub.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.caij.codehub.ui.fragment.BaseFragment;

import java.util.List;

/**
 * Created by Caij on 2015/8/24.
 */
public class FragmentPageAdapter extends FragmentPagerAdapter{

    protected List<BaseFragment> mFragments;

    public FragmentPageAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

}
