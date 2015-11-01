package com.caij.codehub.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.VolleyError;
import com.caij.codehub.R;
import com.caij.codehub.presenter.BasePresent;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.presenter.RepositoryListPresenter;
import com.caij.codehub.ui.listener.RepositoryListUi;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;

/**
 * Created by Caij on 2015/9/21.
 */
public class TrendingRepositoriesFragment extends RepositoriesFragment {

    private RepositoryListPresenter mPresenter;
    private AlertDialog dialog;

    private String since;
    private String language;
    private int sinceCheckRadioId;
    private int languageCheckRadioId;
    private Map<Integer, String> filters = new HashMap<>();
    private RadioGroup sinceRG;
    private RadioGroup languageRG;

    public static RepositoriesFragment newInstance() {
        RepositoriesFragment fragment = new TrendingRepositoriesFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        mListView.setCanLoadMore(false);
        mPresenter = PresenterFactory.newPresentInstance(RepositoryListPresenter.class, RepositoryListUi.class, this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = View.inflate(getContext(), R.layout.dialog_repository_filter, null);
        builder.setTitle(getString(R.string.filter)).setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                since = filters.get(sinceRG.getCheckedRadioButtonId()).toLowerCase();
                language = filters.get(languageRG.getCheckedRadioButtonId()).toLowerCase();
                sinceCheckRadioId = sinceRG.getCheckedRadioButtonId();
                languageCheckRadioId = languageRG.getCheckedRadioButtonId();

                mSwipeRefreshLayout.setRefreshing(true);
                mPresenter.getTrendingRepository(BasePresent.LoadType.REFRESH, since, language);
            }
        }).setNegativeButton(R.string.cancel, null).setView(dialogView);
        dialog = builder.create();
        sinceRG = (RadioGroup) dialogView.findViewById(R.id.time);
        languageRG = (RadioGroup) dialogView.findViewById(R.id.languages);

        initFilterData();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_trending;
    }

    private void initFilterData() {
        since = getString(R.string.daily).toLowerCase();
        language = getString(R.string.all).toLowerCase();
        sinceCheckRadioId = R.id.daily;
        languageCheckRadioId = R.id.all;
        filters.put(R.id.daily, getString(R.string.daily));
        filters.put(R.id.weekly, getString(R.string.weekly));
        filters.put(R.id.monthly, getString(R.string.monthly));
        filters.put(R.id.all, getString(R.string.all));
        filters.put(R.id.c, getString(R.string.c));
        filters.put(R.id.cpp, getString(R.string.cpp));
        filters.put(R.id.java, getString(R.string.java));
        filters.put(R.id.javascript, getString(R.string.javascript));
        filters.put(R.id.objective_c, getString(R.string.objective_c));
        filters.put(R.id.python, getString(R.string.python));
    }

    @Override
    protected void onUserFirstVisible() {
        mPresenter.getTrendingRepository(BasePresent.LoadType.FIRSTLOAD, since, language);
    }

    @Override
    public void onRefresh() {
        mPage.reset();
        mPresenter.getTrendingRepository(BasePresent.LoadType.REFRESH, since, language);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mPresenter.getTrendingRepository(BasePresent.LoadType.FIRSTLOAD, since, language);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void showError(int type, VolleyError error) {
        super.showError(type, error);
        if (type == BasePresent.LoadType.REFRESH) {
            mPage.scrollBack(); //用于刷新的时候重置page刷新错误，导致下拉index出错。
        }
    }

    @OnClick(R.id.filter)
    public void onFilterClick() {
        ((RadioButton)sinceRG.findViewById(sinceCheckRadioId)).setChecked(true);
        ((RadioButton)languageRG.findViewById(languageCheckRadioId)).setChecked(true);
        dialog.show();
    }
}
