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

import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.R;
import com.caij.codehub.present.ui.BaseUi;
import com.caij.codehub.widgets.swipeback.app.SwipeBackActivity;
import com.caij.lib.utils.AppManager;
import com.caij.lib.utils.ToastUtil;
import com.umeng.analytics.MobclickAgent;

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
    LinearLayout mLoadingLinearLayout;
    ImageView mAnimLoadingImage;
    ProgressBar mLoadingProgressBar;
    ViewGroup mContentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutId());
        mContentContainer = (ViewGroup) findViewById(getContentContainerViewId());
        if ((getAttachLayoutId() >>> 24) >= 2) {  //is resource id.
            getLayoutInflater().inflate(getAttachLayoutId(), mContentContainer, true);
        }
        mLoadErrorViewStub = (ViewStub) findViewById(R.id.vs_load_error);
        mAnimLoadingViewStub = (ViewStub) findViewById(R.id.vs_anim_loading);
        mProLoadingViewStub = (ViewStub) findViewById(R.id.vs_progress_bar_loading);
        ButterKnife.bind(this);
    }

    protected int getContentLayoutId() {
        return R.layout.activity_base_codehub;
    }

    protected abstract int getAttachLayoutId();

    protected int getContentContainerViewId() {
        return R.id.fl_content_container;
    }


    public void onReFreshBtnClick(View view) {
        showError(false);
    }

    protected void showError(boolean isVisible) {
        if (isVisible) {
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
        }else {
            if (mLoadErrorLinearLayout != null) {
                mLoadErrorLinearLayout.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showContentAnimLoading(boolean isVisible) {
        if (isVisible) {
            if (mLoadingLinearLayout == null) {
                View view = mAnimLoadingViewStub.inflate();
                mLoadingLinearLayout = (LinearLayout) view.findViewById(R.id.rl_anim_loading);
                mAnimLoadingImage = (ImageView) mLoadingLinearLayout.findViewById(R.id.iv_anim_loading);
            }
            mLoadingLinearLayout.setVisibility(View.VISIBLE);
            ((AnimationDrawable) mAnimLoadingImage.getDrawable()).start();
        }else {
            if (mLoadingLinearLayout != null) {
                ((AnimationDrawable) mAnimLoadingImage.getDrawable()).stop();
                mLoadingLinearLayout.setVisibility(View.GONE);
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
        AppManager.getInstance().finishAllActivity();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showError(int msgId) {
        ToastUtil.show(this, msgId);
    }

    @Override
    public void showContentError() {
        showError(true);
    }
}
