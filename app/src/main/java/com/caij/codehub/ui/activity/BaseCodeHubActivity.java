package com.caij.codehub.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.R;
import com.caij.lib.utils.AppManager;
import com.caij.lib.utils.ToastUtil;
import com.caij.lib.utils.VolleyManager;
import com.caij.lib.volley.request.JsonParseError;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/9/19.
 */
public abstract class BaseCodeHubActivity extends BaseActivity{

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

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
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

    protected void processVolleyError(VolleyError error) {
        if (error instanceof ServerError || error instanceof JsonParseError) {
            ToastUtil.show(this, R.string.server_error_hint);
        }else if(error instanceof NetworkError || error instanceof TimeoutError) {
            ToastUtil.show(this, R.string.network_error_hint);
        }else if (error instanceof AuthFailureError) {
            AppManager.getInstance().finishAllActivity();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            //clear cahce data
            CodeHubPrefs.get().logout();
            ToastUtil.show(this, R.string.account_error_hint);
        }else {
            ToastUtil.show(this, R.string.data_load_error_hint);
        }
    }

    protected void setToolbarTitle(String title) {
        if (title.length() > 12) {
            title = title.substring(0, 12) + "...";
        }
        getSupportActionBar().setTitle(title);
    }

    public String getToken() {
        String token = CodeHubPrefs.get().getToken();
        if (TextUtils.isEmpty(token)) {
            AppManager.getInstance().finishAllActivity();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            //clear cahce data
            CodeHubPrefs.get().logout();
            ToastUtil.show(this, R.string.account_error_hint);
        }
        return token;
    }
}
