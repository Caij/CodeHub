package com.caij.codehub.present;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.caij.codehub.R;
import com.caij.codehub.present.ui.BaseUi;
import com.caij.codehub.interactor.UiCallBack;
import com.caij.lib.utils.VolleyManager;
import com.caij.lib.volley.request.JsonParseError;

import java.lang.ref.WeakReference;

/**
 * Created by Caij on 2015/11/16.
 */
public abstract class DefaultInteractorCallback<E> implements UiCallBack<E>{

    private WeakReference<? extends BaseUi> mUi;

    public DefaultInteractorCallback(WeakReference<? extends BaseUi> ui) {
        this.mUi = ui;
    }

    @Override
    public void onError(VolleyError error) {
        onError(dealWithVolleyError(error));
    }

    private int dealWithVolleyError(VolleyError error) {
        int msgId;
        if (error instanceof ServerError || error instanceof JsonParseError) {
            msgId = R.string.server_error_hint;
        }else if(error instanceof NetworkError || error instanceof TimeoutError) {
            msgId = R.string.network_error_hint;
        }else if (error instanceof AuthFailureError) {
            msgId = R.string.account_error_hint;
            VolleyManager.cancelAllRequest();
            BaseUi baseUi = mUi.get();
            if (baseUi != null) {
                baseUi.onAuthError();
            }
        }else {
            msgId = R.string.data_load_error_hint;
        }
        return msgId;
    }

    public abstract void onError(int msgId);
}
