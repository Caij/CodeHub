package com.caij.codehub.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.R;
import com.caij.codehub.present.ui.BaseUi;
import com.caij.lib.utils.AppManager;
import com.caij.lib.utils.ToastUtil;
import com.caij.lib.utils.VolleyManager;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:  Caij
 * Email:   worldcaij@gmail.com
 * Date:    2015/11/13
 * Description:
 */
public abstract class BaseCodeHubActivity extends BaseActivity implements BaseUi{

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
        mRequestTag = createRequestTag();
        setContentView(getContentViewId());
        mContentContainer = (ViewGroup) findViewById(R.id.base_code_hub_container);
        if (getAttachLayoutId() != 0) {
            getLayoutInflater().inflate(getAttachLayoutId(), mContentContainer, true);
        }
        ButterKnife.bind(this);
    }

    protected int getContentViewId() {
        return R.layout.activity_base_codehub;
    }

    protected abstract int getAttachLayoutId();

    protected Object getRequestTag() {
        return mRequestTag;
    }

    protected Object createRequestTag() {
        return new Object();
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

    public void showLoading() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (mRequestTag != null) {
            VolleyManager.cancelRequestByTag(mRequestTag);
        }
    }

    @Override
    public void onAuthError() {
        CodeHubPrefs.get().logout();
        AppManager.getInstance().finishAllActivityExcept(this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(int msgId) {
        ToastUtil.show(this, msgId);
    }

    @Override
    public void showErrorView() {
        showError();
    }
}
