package com.caij.codehub.ui.callback;

import com.android.volley.VolleyError;

/**
 * Created by Caij on 2015/11/4.
 */
public interface UiCallBack<E> {

    public void onSuccess(E e);

    public void onLoading();

    public void onError(VolleyError error);

}
