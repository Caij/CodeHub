package com.caij.codehub.ui.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.present.LoadType;
import com.caij.codehub.present.RepositoriesPresent;
import com.caij.codehub.ui.activity.TrendingFilterActivity;
import com.caij.codehub.ui.transitions.FabDialogMorphSetup;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;

/**
 * Created by Caij on 2015/9/21.
 */
public class TrendingRepositoriesFragment extends RepositoriesFragment {

    private AlertDialog mDialog;
    private int mSinceCheckRadioId;
    private int mLanguageCheckRadioId;
    private Map<Integer, String> mFilters = new HashMap<>();
    private RadioGroup mSinceRadioGroup;
    private RadioGroup mLanguageRadioGroup;
    private RepositoriesPresent mRepositoriesPresent;

    public static RepositoriesFragment newInstance() {
        RepositoriesFragment fragment = new TrendingRepositoriesFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        getLoadMoreRecyclerView().setLoadMoreEnable(false);
        mRepositoriesPresent = new RepositoriesPresent(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View filterDialogView = View.inflate(getContext(), R.layout.dialog_repository_filter, null);
        builder.setTitle(getString(R.string.filter)).setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int sinceCheckId = mSinceRadioGroup.getCheckedRadioButtonId();
                int languageCheckId = mLanguageRadioGroup.getCheckedRadioButtonId();
                if (sinceCheckId != mSinceCheckRadioId || languageCheckId != mLanguageCheckRadioId) {
                    setFilterResult(mSinceRadioGroup.getCheckedRadioButtonId(), mLanguageRadioGroup.getCheckedRadioButtonId());
                }
            }
        }).setNegativeButton(R.string.cancel, null).setView(filterDialogView);
        mDialog = builder.create();
        mSinceRadioGroup = (RadioGroup) filterDialogView.findViewById(R.id.time);
        mLanguageRadioGroup = (RadioGroup) filterDialogView.findViewById(R.id.languages);

        initFilterData();
        if (savedInstanceState != null) {
            mSinceCheckRadioId = savedInstanceState.getInt(Constant.TRENDING_REPOSITORY_CHECK_SINCE_ID, R.id.daily);
            mLanguageCheckRadioId = savedInstanceState.getInt(Constant.TRENDING_REPOSITORY_CHECK_LANGUAGE_ID, R.id.all);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(Constant.TRENDING_REPOSITORY_CHECK_SINCE_ID, mSinceCheckRadioId);
        outState.putInt(Constant.TRENDING_REPOSITORY_CHECK_LANGUAGE_ID, mSinceCheckRadioId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected int getAttachLayoutId() {
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
        mRepositoriesPresent.getTrendingRepository(LoadType.FIRST, since, language);
    }

    @Override
    public void onRefresh() {
        String since = mFilters.get(mSinceCheckRadioId).toLowerCase();
        String language = mFilters.get(mLanguageCheckRadioId).toLowerCase();
        mRepositoriesPresent.getTrendingRepository(LoadType.REFRESH, since, language);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        String since = mFilters.get(mSinceCheckRadioId).toLowerCase();
        String language = mFilters.get(mLanguageCheckRadioId).toLowerCase();
        mRepositoriesPresent.getTrendingRepository(LoadType.FIRST, since, language);
    }


    @OnClick(R.id.filter)
    public void onFilterClick(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(getActivity(), TrendingFilterActivity.class);
            ActivityOptionsCompat optionsCompat
                    = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    getActivity(), view, Constant.TRANSIT_PIC);
            intent.putExtra(FabDialogMorphSetup.EXTRA_SHARED_ELEMENT_START_COLOR,
                    ContextCompat.getColor(getActivity(), R.color.color_accent));
            intent.putExtra(Constant.TRENDING_REPOSITORY_CHECK_SINCE_ID, mSinceCheckRadioId);
            intent.putExtra(Constant.TRENDING_REPOSITORY_CHECK_LANGUAGE_ID, mLanguageCheckRadioId);
            getActivity().startActivityForResult(intent, Constant.FILTER_REQUEST_CODE, optionsCompat.toBundle());
        }else {
            ((RadioButton) mSinceRadioGroup.findViewById(mSinceCheckRadioId)).setChecked(true);
            ((RadioButton) mLanguageRadioGroup.findViewById(mLanguageCheckRadioId)).setChecked(true);
            mDialog.show();
        }
    }

    private void setFilterResult(int sinceId, int languageId) {
        mSinceCheckRadioId = sinceId;
        mLanguageCheckRadioId = languageId;

        String since = mFilters.get(mSinceCheckRadioId).toLowerCase();
        String language = mFilters.get(mLanguageCheckRadioId).toLowerCase();

        mSwipeRefreshLayout.setRefreshing(true);
        mRepositoriesPresent.getTrendingRepository(LoadType.REFRESH, since, language);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.FILTER_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            int sinceCheckId = data.getIntExtra(Constant.TRENDING_REPOSITORY_CHECK_SINCE_ID, R.id.daily);
            int languageCheckId = data.getIntExtra(Constant.TRENDING_REPOSITORY_CHECK_LANGUAGE_ID, R.id.all);
            setFilterResult(sinceCheckId, languageCheckId);
        }
    }

    @Override
    public void onDestroyView() {
        mRepositoriesPresent.onDeath();
        super.onDestroyView();
    }

}
