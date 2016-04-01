package com.caij.codehub.network;

import android.app.Activity;
import android.content.Intent;

import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.R;
import com.caij.codehub.ui.activity.LoginActivity;
import com.caij.util.AppManager;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;

/**
 * Created by Caij on 2016/4/1.
 */
public abstract class NetWorkDefaultObserver<T> implements Observer<T> {

    private Activity activity;

    public NetWorkDefaultObserver(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onError(Throwable e) {
        int errorHintStringId = R.string.data_load_error_hint;
        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            if (exception.response() == null) {
                errorHintStringId = R.string.network_error_hint;
            }else if (exception.code() == 401 || exception.code() == 403) { //AuthFailureError
                errorHintStringId = R.string.account_error_hint;

                CodeHubPrefs.get().logout();
                AppManager.getInstance().finishAllActivity();
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
            }
        }
        onError(e, errorHintStringId);
    }

    protected abstract void onError(Throwable e, int errorHintStringId);

}
