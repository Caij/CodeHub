package com.caij.codehub.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.caij.codehub.R;
import com.caij.codehub.presenter.BasePresent;
import com.caij.codehub.ui.activity.LoginActivity;
import com.caij.codehub.ui.listener.BaseUi;
import com.caij.lib.utils.AppManager;
import com.caij.lib.utils.ToastUtil;
import com.caij.lib.volley.request.JsonParseError;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/9/20.
 */
public abstract class BaseCodeHubFragment extends BaseFragment implements BaseUi {

    @Nullable
    @Bind(R.id.pb_content_loading)
    ProgressBar pbLoading;

    @Nullable
    @Bind(R.id.ll_load_error)
    LinearLayout llLoadError;

    ViewGroup content;
    @Bind(R.id.btn_refresh)
    Button btnRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_code_hub, container, false);
        content = (ViewGroup) view.findViewById(R.id.content);
        getActivity().getLayoutInflater().inflate(getContentLayoutId(), content, true);
        ButterKnife.bind(this, view);
        return view;
    }

    protected abstract int getContentLayoutId();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onReFreshBtnClick(view);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showError(int type, VolleyError error) {
        if (llLoadError != null && type == BasePresent.LoadType.FIRSTLOAD) {
            llLoadError.setVisibility(View.VISIBLE);
        }
        processVolleyError(error);
    }

    public void hideError() {
        if (llLoadError != null) {
            llLoadError.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoading(int loadType) {
        if (pbLoading != null && loadType == BasePresent.LoadType.FIRSTLOAD) {
            pbLoading.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading(int loadType) {
        if (pbLoading != null && loadType == BasePresent.LoadType.FIRSTLOAD) {
            pbLoading.setVisibility(View.GONE);
        }
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
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            ToastUtil.show(getContext(), R.string.account_error_hint);
        }else {
            ToastUtil.show(getContext(), R.string.data_load_error_hint);
        }
    }
}
