package com.caij.codehub.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Token;
import com.caij.codehub.presenter.LoginPresenter;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.ui.listener.LoginUi;
import com.caij.lib.utils.SPUtils;
import com.caij.lib.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Caij on 2015/8/26.
 */
public class LoginActivity extends BaseCodeHubActivity implements LoginUi {


    @Bind(R.id.edit_username)
    EditText mEditUsername;
    @Bind(R.id.edit_pwd)
    EditText mEditPassword;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setToolbarTitle(getString(R.string.action_login));
        mPresenter = PresenterFactory.newPresentInstance(LoginPresenter.class, LoginUi.class, this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login;
    }


    @OnClick(R.id.button_login)
    public void onSubmit() {
        mPresenter.login(mEditUsername.getText().toString(), mEditPassword.getText().toString());
    }

    @Override
    public void onLoginSuccess(Token token) {
        ToastUtil.show(this, "Login Success");
        SPUtils.save(Constant.USER_TOKEN, token.getToken());
        SPUtils.save(Constant.USER_NAME, mEditUsername.getText().toString());
        Intent intent = MainActivity.newIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(int type, VolleyError msg) {
        ToastUtil.show(this, "Login Error");
    }
}
