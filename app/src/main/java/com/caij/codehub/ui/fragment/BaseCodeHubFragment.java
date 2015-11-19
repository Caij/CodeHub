package com.caij.codehub.ui.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
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
import com.caij.codehub.ui.activity.LoginActivity;
import com.caij.lib.utils.AppManager;
import com.caij.lib.utils.ToastUtil;

import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/9/20.
 */
public abstract class BaseCodeHubFragment extends BaseFragment implements BaseUi{

    ViewStub mAnimLoadingViewStub;
    ViewStub mProLoadingViewStub;
    ViewStub mLoadErrorViewStub;
    LinearLayout mLoadErrorLinearLayout;
    LinearLayout mLoadingLinearLayout;
    ImageView mAnimLoadingImage;
    ProgressBar mLoadingProgressBar;
    ViewGroup mContentContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_code_hub, container, false);
        mContentContainer = (ViewGroup) view.findViewById(R.id.fl_content_container);
        if (getAttachLayoutId() !=  0) {
            getActivity().getLayoutInflater().inflate(getAttachLayoutId(), mContentContainer, true);
        }
        mLoadErrorViewStub = (ViewStub) view.findViewById(R.id.vs_load_error);
        mAnimLoadingViewStub = (ViewStub) view.findViewById(R.id.vs_anim_loading);
        mProLoadingViewStub = (ViewStub) view.findViewById(R.id.vs_progress_bar_loading);
        ButterKnife.bind(this, view);
        return view;
    }

    protected abstract int getAttachLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
            if (mLoadingLinearLayout != null) {
                mLoadingProgressBar.setVisibility(View.GONE);
            }
        }
    }

    public void showContentContainer() {
        mContentContainer.setVisibility(View.VISIBLE);
    }

    public void onReFreshBtnClick(View view) {
        hideError();
    }


    @Override
    public void onAuthError() {
        CodeHubPrefs.get().logout();
        AppManager.getInstance().finishAllActivityExcept(getActivity());
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void showError(int msgId) {
        ToastUtil.show(getActivity(), msgId);
    }

    @Override
    public void showContentError() {
        showError();
    }
}
