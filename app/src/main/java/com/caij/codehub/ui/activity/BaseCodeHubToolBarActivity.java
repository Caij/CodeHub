package com.caij.codehub.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.caij.codehub.R;
import com.caij.lib.utils.VolleyManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/9/19.
 */
public abstract class BaseCodeHubToolBarActivity extends BaseCodeHubActivity{

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Nullable
    @Bind(R.id.pb_content_loading)
    ProgressBar mLoadingProgressBar;

    @Bind(R.id.vs_load_error)
    ViewStub mLoadErrorViewStub;

    LinearLayout mLoadErrorLinearLayout;

    ViewGroup mContentContainer;

    private Object mRequestTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSystemBarTintColor(getResources().getColor(R.color.theme_color));
        setContentView(R.layout.activity_base_toolbar);
        mContentContainer = (ViewGroup) findViewById(R.id.base_code_hub_container);
        getLayoutInflater().inflate(getContentLayoutId(), mContentContainer, true);
        ButterKnife.bind(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRequestTag = createRequestTag();
    }

    protected abstract int getContentLayoutId();

    protected Object createRequestTag() {
        return new Object();
    }

    protected Object getRequestTag() {
        return mRequestTag;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (mRequestTag != null) {
            VolleyManager.cancelRequestByTag(mRequestTag);
        }
    }

    public void onReFreshBtnClick(View view) {
        hideError();
    }

    protected  void hideError(){
        if (mLoadErrorLinearLayout != null) {
            mLoadErrorLinearLayout.setVisibility(View.GONE);
        }
    }

    protected void showError() {
        if (mLoadErrorLinearLayout == null) {
            View view = mLoadErrorViewStub.inflate();
            mLoadErrorLinearLayout = (LinearLayout) view.findViewById(R.id.ll_load_error);
            Button btnRefresh = (Button) view.findViewById(R.id.btn_refresh);
            btnRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onReFreshBtnClick(view);
                }
            });
        }
        mLoadErrorLinearLayout.setVisibility(View.VISIBLE);
    }

    protected void showLoading() {
        if (mLoadingProgressBar != null) {
            mLoadingProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoading() {
        if (mLoadingProgressBar != null) {
            mLoadingProgressBar.setVisibility(View.GONE);
        }
    }

    public void showContentContainer() {
        mContentContainer.setVisibility(View.VISIBLE);
    }

    protected void setToolbarTitle(String title) {
        if (title.length() > 12) {
            title = title.substring(0, 12) + "...";
        }
        getSupportActionBar().setTitle(title);
    }

}
