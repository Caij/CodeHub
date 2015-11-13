package com.caij.codehub.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.caij.codehub.Constant;
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

                mSinceCheckRadioId = mSinceRadioGroup.getCheckedRadioButtonId();
                mLanguageCheckRadioId = mLanguageRadioGroup.getCheckedRadioButtonId();

                String since = mFilters.get(mSinceCheckRadioId).toLowerCase();
                String language = mFilters.get(mLanguageCheckRadioId).toLowerCase();

                mSwipeRefreshLayout.setRefreshing(true);
                mRepositoryListPresenter.getTrendingRepository(since, language, getRequestTag() , mLoadRefreshUiCallBack);
            }
        }).setNegativeButton(R.string.cancel, null).setView(mFilterDialogView);
        mDialog = builder.create();
        mSinceRadioGroup = (RadioGroup) mFilterDialogView.findViewById(R.id.time);
        mLanguageRadioGroup = (RadioGroup) mFilterDialogView.findViewById(R.id.languages);

        initFilterData();
        if (savedInstanceState != null) {
            mSinceCheckRadioId = savedInstanceState.getInt(Constant.TRENDING_REPOSITORY_CHECK_SINCE_ID, R.id.daily);
            mLanguageCheckRadioId = savedInstanceState.getInt(Constant.TRENDING_REPOSITORY_CHECK_LANGUAGE_ID, R.id.all);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constant.TRENDING_REPOSITORY_CHECK_SINCE_ID, mSinceCheckRadioId);
        outState.putInt(Constant.TRENDING_REPOSITORY_CHECK_LANGUAGE_ID, mSinceCheckRadioId);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_trending;
    }

    private void initFilterData() {
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
        String since = mFilters.get(mSinceCheckRadioId).toLowerCase();
        String language = mFilters.get(mLanguageCheckRadioId).toLowerCase();
        mRepositoryListPresenter.getTrendingRepository(since, language, getRequestTag(), mFirstLoadUiCallBack);
    }

    @Override
    public void onRefresh() {
        String since = mFilters.get(mSinceCheckRadioId).toLowerCase();
        String language = mFilters.get(mLanguageCheckRadioId).toLowerCase();
        mRepositoryListPresenter.getTrendingRepository(since, language, getRequestTag(), mLoadRefreshUiCallBack);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        String since = mFilters.get(mSinceCheckRadioId).toLowerCase();
        String language = mFilters.get(mLanguageCheckRadioId).toLowerCase();
        mRepositoryListPresenter.getTrendingRepository(since, language, getRequestTag(), mFirstLoadUiCallBack);
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
