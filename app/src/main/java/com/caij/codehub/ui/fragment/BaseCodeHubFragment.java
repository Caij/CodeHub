package com.caij.codehub.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
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
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.ui.activity.LoginActivity;
import com.caij.lib.utils.AppManager;
import com.caij.lib.utils.SPUtils;
import com.caij.lib.utils.ToastUtil;
import com.caij.lib.utils.VolleyManager;
import com.caij.lib.volley.request.JsonParseError;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/9/20.
 */
public abstract class BaseCodeHubFragment extends BaseFragment{

    @Nullable
    @Bind(R.id.pb_content_loading)
    ProgressBar mLoadingProgressBar;

    ViewStub mLoadErrorViewStub;

    LinearLayout mLoadErrorLinearLayout;

    ViewGroup mContentContainer;

    private Object mRequestTag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_code_hub, container, false);
        mContentContainer = (ViewGroup) view.findViewById(R.id.content);
        getActivity().getLayoutInflater().inflate(getContentLayoutId(), mContentContainer, true);
        mLoadErrorViewStub = (ViewStub) view.findViewById(R.id.vs_load_error);
        ButterKnife.bind(this, view);
        mRequestTag = createRequestTag();
        return view;
    }

    protected abstract int getContentLayoutId();

    protected Object createRequestTag() {
        return new Object();
    }

    protected Object getRequestTag() {
        return mRequestTag;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (mRequestTag != null) {
            VolleyManager.cancelRequestByTag(this);
        }
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
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mContentContainer, "alpha", 0.4f, 1f);
//        animator.setDuration(800);
//        animator.start();
    }

    public void onReFreshBtnClick(View view) {
        hideError();
    }

    protected void processVolleyError(VolleyError error) {
        if (error instanceof ServerError || error instanceof JsonParseError) {
            ToastUtil.show(getContext(), R.string.server_error_hint);
        }else if(error instanceof NetworkError || error instanceof TimeoutError) {
            ToastUtil.show(getContext(), R.string.network_error_hint);
        }else if (error instanceof AuthFailureError) {
            AppManager.getInstance().finishAllActivity();
            CodeHubPrefs.get().logout();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            ToastUtil.show(getContext(), R.string.account_error_hint);
        }else {
            ToastUtil.show(getContext(), R.string.data_load_error_hint);
        }
    }

    public String getToken() {
        String token = SPUtils.getString(Constant.USER_TOKEN, "");
        if (TextUtils.isEmpty(token)) {
            AppManager.getInstance().finishAllActivity();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            //clear cahce data
            CodeHubPrefs.get().logout();
            ToastUtil.show(getActivity(), R.string.account_error_hint);
        }
        return token;
    }
}
