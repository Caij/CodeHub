package com.caij.codehub.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.caij.codehub.CodeHubApplication;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.presenter.LoginPresenter;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.ui.callback.UiCallBack;
import com.caij.codehub.utils.FileUtil;
import com.caij.lib.utils.AppManager;
import com.caij.lib.utils.SPUtils;
import com.caij.lib.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by Caij on 2015/11/3.
 */
public class SettingActivity extends BaseCodeHubActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle("Setting");
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_setting;
    }

    @OnClick(R.id.rl_login_out)
    public void onLoginOutClick() {
        LoginPresenter loginPresenter = PresenterFactory.newPresentInstance(LoginPresenter.class);
        long tokenId = SPUtils.getLong(Constant.USER_TOKEN_ID, -1);
        String username = CodeHubApplication.getCurrentUserName();
        String pwd = SPUtils.getString(Constant.USER_PWD, "");
        loginPresenter.loginOut(pwd, username, String.valueOf(tokenId), this, new UiCallBack<NetworkResponse>() {
            @Override
            public void onSuccess(NetworkResponse response) {
                hideLoading();
                clearDataAndGotoLogin();
            }

            @Override
            public void onLoading() {
                showLoading();
            }

            @Override
            public void onError(VolleyError error) {
                hideLoading();
                if (error != null && error.networkResponse!= null && error.networkResponse.statusCode == 401) {
                    clearDataAndGotoLogin();
                }else {
                    ToastUtil.show(SettingActivity.this, R.string.login_out_error);
                }
            }
        });
    }

    private void clearDataAndGotoLogin() {
        SPUtils.saveString(Constant.USER_TOKEN, "");
        SPUtils.saveString(Constant.USER_NAME, "");
        AppManager.getInstance().finishAllActivity();
        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
