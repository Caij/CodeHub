package com.caij.codehub.ui.callback;

import android.app.Activity;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.R;
import com.caij.codehub.ui.activity.LoginActivity;
import com.caij.lib.utils.AppManager;
import com.caij.lib.utils.ToastUtil;
import com.caij.lib.utils.VolleyManager;
import com.caij.lib.volley.request.JsonParseError;

/**
 * Created by Caij on 2015/11/13.
 */
public abstract class DefaultUiCallBack<E> implements UiCallBack<E>{

    private Activity mActivity;

    public DefaultUiCallBack(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void onError(VolleyError error) {
        dealWithVolleyError(error);
        onDefaultError(error);
    }

    public abstract void onDefaultError(VolleyError error);

    private void dealWithVolleyError(VolleyError error) {
        if (error instanceof ServerError || error instanceof JsonParseError) {
            ToastUtil.show(mActivity, R.string.server_error_hint);
        }else if(error instanceof NetworkError || error instanceof TimeoutError) {
            ToastUtil.show(mActivity, R.string.network_error_hint);
        }else if (error instanceof AuthFailureError) {
            ToastUtil.show(mActivity, R.string.account_error_hint);

            //clear cahce data
            CodeHubPrefs.get().logout();
            VolleyManager.cancelAllRequest();

            AppManager.getInstance().finishAllActivityExcept(mActivity);
            Intent intent = new Intent(mActivity, LoginActivity.class);
            mActivity.startActivity(intent);
            mActivity.finish();
        }else {
            ToastUtil.show(mActivity, R.string.data_load_error_hint);
        }
    }
}
