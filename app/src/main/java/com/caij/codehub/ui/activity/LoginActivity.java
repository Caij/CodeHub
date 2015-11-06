package com.caij.codehub.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.caij.codehub.CodeHubApplication;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Token;
import com.caij.codehub.presenter.LoginPresenter;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.ui.callback.UiCallBack;
import com.caij.lib.utils.SPUtils;
import com.caij.lib.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Caij on 2015/8/26.
 */
public class LoginActivity extends BaseCodeHubActivity implements UiCallBack<Token> {


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
        mPresenter = PresenterFactory.newPresentInstance(LoginPresenter.class);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.button_login)
    public void onSubmit() {
        mPresenter.login(mEditUsername.getText().toString(), mEditPassword.getText().toString(), this, this);
    }


    @Override
    public void onSuccess(Token token) {
        hideLoading();
        saveToken(token.getToken());
        saveTokenId(token.getId());
        CodeHubApplication.saveCurrentUserName(mEditUsername.getText().toString());
        savePwd(mEditPassword.getText().toString());
        Intent intent = MainActivity.newIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoading() {
        showLoading();
    }

    @Override
    public void onError(VolleyError error) {
        hideLoading();
        ToastUtil.show(this, R.string.login_error);
    }

    private void saveToken(String token) {
        SPUtils.saveString(Constant.USER_TOKEN, token);
    }

    private void saveTokenId(long tokenId) {
        SPUtils.saveString(Constant.USER_TOKEN_ID, tokenId);
    }

    private void savePwd(String pwd) {
        SPUtils.saveString(Constant.USER_PWD, pwd);
    }
}
