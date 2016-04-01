package com.caij.codehub.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.EditText;

import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.R;
import com.caij.codehub.bean.Token;
import com.caij.codehub.network.NetWorkDefaultObserver;
import com.caij.codehub.present.UserLoginPresent;
import com.caij.util.AppManager;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.Subscription;

/**
 * Created by Caij on 2015/8/26.
 */
public class LoginActivity extends BaseCodeHubToolBarActivity {

    private static final String TAG = "LoginActivity";
    @Bind(R.id.edit_username)
    EditText mEditUsername;
    @Bind(R.id.edit_pwd)
    EditText mEditPassword;
    private ProgressDialog mLoginDialog;
    private UserLoginPresent mLoginPresent;
    private Subscription mLoginSubscription;

    @Override
    protected void handleIntent(Intent intent) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setTitle(getString(R.string.action_login));
        mLoginPresent = new UserLoginPresent();
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
        mLoginDialog.show();
        mLoginSubscription = mLoginPresent.login(mEditUsername.getText().toString(), mEditPassword.getText().toString())
                .subscribe( new NetWorkDefaultObserver<Token>(this) {

                    @Override
                    protected void onError(Throwable e, int errorHintStringId) {
                        mLoginDialog.dismiss();
                        showError(errorHintStringId);
                    }

                    @Override
                    public void onError(Throwable e) {
                        int errorHintStringId = R.string.data_load_error_hint;
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            if (exception.response() == null) {
                                errorHintStringId = R.string.network_error_hint;
                            }else if (exception.code() == 401 || exception.code() == 403) { //AuthFailureError
                                errorHintStringId = R.string.password_error;
                            }
                        }
                        onError(e, errorHintStringId);
                    }

                    @Override
                    public void onCompleted() {
                    }


                    @Override
                    public void onNext(Token token) {
                        mLoginDialog.dismiss();
                        onLoginSuccess(token);
                    }
                });
    }

    public void onLoginSuccess(Token token) {
        CodeHubPrefs.get().setToken(token);
        CodeHubPrefs.get().setUsernameAndPwd(mEditUsername.getText().toString(), mEditPassword.getText().toString());
        Intent intent = MainActivity.newIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }

    private void unsubscribe() {
        if (mLoginSubscription != null && mLoginSubscription.isUnsubscribed()){
            mLoginSubscription.unsubscribe();
        }
    }

    @Override
    protected void onDestroy() {
        mLoginDialog = null;
        unsubscribe();
        super.onDestroy();
    }

}
