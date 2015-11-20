package com.caij.codehub.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.EditText;

import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.R;
import com.caij.codehub.bean.Token;
import com.caij.codehub.present.UserLoginPresent;
import com.caij.codehub.present.ui.UserLoginUi;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Caij on 2015/8/26.
 */
public class LoginActivity extends BaseCodeHubToolBarActivity implements UserLoginUi {

    @Bind(R.id.edit_username)
    EditText mEditUsername;
    @Bind(R.id.edit_pwd)
    EditText mEditPassword;
    private ProgressDialog mLoginDialog;
    private UserLoginPresent mLoginPresent;

    @Override
    protected void handleIntent(Intent intent) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setTitle(getString(R.string.action_login));
        mLoginPresent = new UserLoginPresent(this);
        mLoginDialog = new ProgressDialog(this);
        mLoginDialog.setMessage(getString(R.string.logining));
        mLoginDialog.setCancelable(false);
        mLoginDialog.setProgressStyle(R.style.AppCompatAlertDialogStyle);
    }

    @Override
    protected int getAttachLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.button_login)
    public void onSubmit() {
        mLoginPresent.login(mEditUsername.getText().toString(), mEditPassword.getText().toString());
    }

    @Override
    protected boolean isSwipeBackEnable() {
        return false;
    }

    @Override
    public void onLoginSuccess(Token token) {
        CodeHubPrefs.get().setToken(token);
        CodeHubPrefs.get().setUsernameAndPwd(mEditUsername.getText().toString(), mEditPassword.getText().toString());
        Intent intent = MainActivity.newIntent(this);
        startActivity(intent);
        finish();
    }


    @Override
    public void showProgressBarLoading(boolean isVisible) {
        if (isVisible) {
            mLoginDialog.show();
        }else {
            mLoginDialog.dismiss();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginDialog = null;
        mLoginPresent.onDeath();
    }

}
