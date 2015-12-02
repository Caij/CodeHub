package com.caij.codehub.ui.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.R;
import com.caij.codehub.present.SettingPresent;
import com.caij.codehub.present.ui.SettingUi;
import com.caij.lib.utils.AppManager;

import butterknife.OnClick;


/**
 * Created by Caij on 2015/11/3.
 */
public class SettingActivity extends BaseCodeHubToolBarActivity implements DialogInterface.OnClickListener, SettingUi {

    private ProgressDialog mLogoutLoadingDialog;
    private AlertDialog mLogoutConfirmDialog;
    private SettingPresent mSettingPresent;

    @Override
    protected void handleIntent(Intent intent) {
        setTitle(getString(R.string.action_settings));
        mLogoutConfirmDialog = new AlertDialog.Builder(this).
                setMessage(R.string.wether_logout).
                setPositiveButton(getString(R.string.ok), this).
                setNegativeButton(getString(R.string.cancel), null).
                create();
        mLogoutLoadingDialog = new ProgressDialog(this);
        mLogoutLoadingDialog.setMessage(getString(R.string.loginout));
        mLogoutLoadingDialog.setProgressStyle(R.style.AppCompatAlertDialogStyle);
        mLogoutLoadingDialog.setCancelable(false);

        mSettingPresent = new SettingPresent(this);
    }

    @Override
    protected int getAttachLayoutId() {
        return R.layout.activity_setting;
    }

    @OnClick(R.id.rl_login_out)
    public void onLogoutClick() {
        mLogoutConfirmDialog.show();
    }

    private void logout() {
        String tokenId = CodeHubPrefs.get().getTokenId();
        String baseUsernameAndPwd = CodeHubPrefs.get().getBase64UsernameAndPwd();
        mSettingPresent.logout(baseUsernameAndPwd, tokenId);
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

    @Override
    public void logoutSuccess() {
        clearDataAndGotoLogin();
    }

    @Override
    public void showLogoutLoading(boolean isVisible) {
        if (isVisible) {
            mLogoutLoadingDialog.show();
        }else {
            mLogoutLoadingDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        mSettingPresent.onDeath();
        super.onDestroy();
    }
}
