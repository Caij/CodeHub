package com.caij.codehub.ui.fragment;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.caij.codehub.R;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.presenter.RepositoryListPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;

/**
 * Created by Caij on 2015/9/21.
 */
public class TrendingRepositoriesFragment extends RepositoriesFragment {

    private RepositoryListPresenter mRepositoryListPresenter;
    private AlertDialog mDialog;

    private String mSince;
    private String mLanguage;
    private int mSinceCheckRadioId;
    private int mLanguageCheckRadioId;
    private Map<Integer, String> mFilters = new HashMap<>();
    private RadioGroup mSinceRadioGroup;
    private RadioGroup mLanguageRadioGroup;
    private View mFilterDialogView;

    public static RepositoriesFragment newInstance() {
        RepositoriesFragment fragment = new TrendingRepositoriesFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        getLoadMoreRecyclerView().setLoadMoreEnable(false);

        mRepositoryListPresenter = PresenterFactory.newPresentInstance(RepositoryListPresenter.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mFilterDialogView = View.inflate(getContext(), R.layout.dialog_repository_filter, null);
        builder.setTitle(getString(R.string.filter)).setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSince = mFilters.get(mSinceRadioGroup.getCheckedRadioButtonId()).toLowerCase();
                mLanguage = mFilters.get(mLanguageRadioGroup.getCheckedRadioButtonId()).toLowerCase();
                mSinceCheckRadioId = mSinceRadioGroup.getCheckedRadioButtonId();
                mLanguageCheckRadioId = mLanguageRadioGroup.getCheckedRadioButtonId();

                mSwipeRefreshLayout.setRefreshing(true);
                mRepositoryListPresenter.getTrendingRepository(mSince, mLanguage, getRequestTag() , mLoadRefreshUiCallBack);
            }
        }).setNegativeButton(R.string.cancel, null).setView(mFilterDialogView);
        mDialog = builder.create();
        mSinceRadioGroup = (RadioGroup) mFilterDialogView.findViewById(R.id.time);
        mLanguageRadioGroup = (RadioGroup) mFilterDialogView.findViewById(R.id.languages);

        initFilterData();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_trending;
    }

    private void initFilterData() {
        mSince = getString(R.string.daily).toLowerCase();
        mLanguage = getString(R.string.all).toLowerCase();
        mSinceCheckRadioId = R.id.daily;
        mLanguageCheckRadioId = R.id.all;
        mFilters.put(R.id.daily, getString(R.string.daily));
        mFilters.put(R.id.weekly, getString(R.string.weekly));
        mFilters.put(R.id.monthly, getString(R.string.monthly));
        mFilters.put(R.id.all, getString(R.string.all));
        mFilters.put(R.id.c, getString(R.string.c));
        mFilters.put(R.id.cpp, getString(R.string.cpp));
        mFilters.put(R.id.java, getString(R.string.java));
        mFilters.put(R.id.javascript, getString(R.string.javascript));
        mFilters.put(R.id.objective_c, getString(R.string.objective_c));
        mFilters.put(R.id.python, getString(R.string.python));
    }

    @Override
    protected void onUserFirstVisible() {
        mRepositoryListPresenter.getTrendingRepository(mSince, mLanguage, getRequestTag(), mFirstLoadUiCallBack);
    }

    @Override
    public void onRefresh() {
        mRepositoryListPresenter.getTrendingRepository(mSince, mLanguage, getRequestTag(), mLoadRefreshUiCallBack);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mRepositoryListPresenter.getTrendingRepository(mSince, mLanguage, getRequestTag(), mFirstLoadUiCallBack);
    }



    @OnClick(R.id.filter)
    public void onFilterClick(View view) {
        ((RadioButton) mSinceRadioGroup.findViewById(mSinceCheckRadioId)).setChecked(true);
        ((RadioButton) mLanguageRadioGroup.findViewById(mLanguageCheckRadioId)).setChecked(true);
        mDialog.show();
    }

    @Override
    public void onLoadMore() {

    }
}
