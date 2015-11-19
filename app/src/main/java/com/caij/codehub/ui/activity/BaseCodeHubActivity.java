package com.caij.codehub.ui.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.R;
import com.caij.codehub.present.ui.BaseUi;
import com.caij.lib.utils.AppManager;
import com.caij.lib.utils.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import javax.annotation.Nullable;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:  Caij
 * Email:   worldcaij@gmail.com
 * Date:    2015/11/13
 * Description:
 */
public abstract class BaseCodeHubActivity extends BaseActivity implements BaseUi{

    ViewStub mAnimLoadingViewStub;
    ViewStub mProLoadingViewStub;
    ViewStub mLoadErrorViewStub;
    LinearLayout mLoadErrorLinearLayout;
    RelativeLayout mLoadingRelativeLayout;
    ImageView mAnimLoadingImage;
    ProgressBar mLoadingProgressBar;
    ViewGroup mContentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        mContentContainer = (ViewGroup) findViewById(R.id.base_code_hub_container);
        if (getAttachLayoutId() != 0) {
            getLayoutInflater().inflate(getAttachLayoutId(), mContentContainer, true);
        }
        mLoadErrorViewStub = (ViewStub) findViewById(R.id.vs_load_error);
        mAnimLoadingViewStub = (ViewStub) findViewById(R.id.vs_anim_loading);
        mProLoadingViewStub = (ViewStub) findViewById(R.id.vs_progress_bar_loading);
        ButterKnife.bind(this);
    }

    protected int getContentViewId() {
        return R.layout.activity_base_codehub;
    }

    protected abstract int getAttachLayoutId();

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

    @Override
    public void showContentAnimLoading(boolean isVisible) {
        if (isVisible) {
            if (mLoadingRelativeLayout == null) {
                View view = mAnimLoadingViewStub.inflate();
                mLoadingRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl_anim_loading);
                mAnimLoadingImage = (ImageView) mLoadingRelativeLayout.findViewById(R.id.iv_anim_loading);
            }
            mLoadingRelativeLayout.setVisibility(View.VISIBLE);
            ((AnimationDrawable) mAnimLoadingImage.getDrawable()).start();
        }else {
            if (mLoadingRelativeLayout != null) {
                ((AnimationDrawable) mAnimLoadingImage.getDrawable()).stop();
                mLoadingRelativeLayout.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showProgressBarLoading(boolean isVisible) {
        if (isVisible) {
            if (mLoadingProgressBar == null) {
                View view = mProLoadingViewStub.inflate();
                mLoadingProgressBar = (ProgressBar) view.findViewById(R.id.pb_content_loading);
            }
            mLoadingProgressBar.setVisibility(View.VISIBLE);
        }else {
            if (mLoadingProgressBar != null) {
                mLoadingProgressBar.setVisibility(View.GONE);
            }
        }
    }

    public void showContentContainer() {
        mContentContainer.setVisibility(View.VISIBLE);
    }

    public void hideContentContainer() {
        mContentContainer.setVisibility(View.GONE);
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
    public void showContentError() {
        showError();
    }
}
