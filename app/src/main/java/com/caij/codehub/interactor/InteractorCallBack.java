package com.caij.codehub.interactor;

import com.android.volley.VolleyError;

/**
 * Created by Caij on 2015/11/4.
 */
public interface InteractorCallBack<E> {

    public void onSuccess(E e);

    public void onLoading();

    public void onError(VolleyError error);

}
