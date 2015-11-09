package com.caij.codehub.ui.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.R;
import com.caij.codehub.presenter.LoginPresenter;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.ui.callback.UiCallBack;
import com.caij.lib.utils.AppManager;

import butterknife.OnClick;


/**
 * Created by Caij on 2015/11/3.
 */
public class SettingActivity extends BaseCodeHubActivity implements DialogInterface.OnClickListener{


    private ProgressDialog mLogoutLoadingDialog;
    private AlertDialog mLogoutConfirmDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle("Setting");
        mLogoutConfirmDialog = new AlertDialog.Builder(this).
                setMessage("Whether or not logout").
                setPositiveButton(getString(R.string.ok), this).
                setNegativeButton(getString(R.string.cancel), null).
                create();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_setting;
    }

    @OnClick(R.id.rl_login_out)
    public void onLoginOutClick() {
        mLogoutConfirmDialog.show();
    }

    private void logout() {
        LoginPresenter loginPresenter = PresenterFactory.newPresentInstance(LoginPresenter.class);
        String tokenId = CodeHubPrefs.get().getTokenId();
        String username = CodeHubPrefs.get().getUsername();
        String pwd = CodeHubPrefs.get().getPwd();
        loginPresenter.logout(username, pwd, tokenId, getRequestTag(), new UiCallBack<NetworkResponse>() {
            @Override
            public void onSuccess(NetworkResponse response) {
                mLogoutLoadingDialog.dismiss();
                clearDataAndGotoLogin();
            }

            @Override
            public void onLoading() {
                mLogoutLoadingDialog = ProgressDialog.show(SettingActivity.this, null, getString(R.string.loginout), true);
                mLogoutLoadingDialog.setProgressStyle(R.style.AppCompatAlertDialogStyle);
            }

            @Override
            public void onError(VolleyError error) {
                mLogoutLoadingDialog.dismiss();
                if (error != null && error.networkResponse != null && error.networkResponse.statusCode == 404) {
                    clearDataAndGotoLogin();
                }else {
                    processVolleyError(error);
                }
            }
        });
    }

    private void clearDataAndGotoLogin() {
        CodeHubPrefs.get().logout();
        AppManager.getInstance().finishAllActivity();
        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            logout();
        }
    }
}
