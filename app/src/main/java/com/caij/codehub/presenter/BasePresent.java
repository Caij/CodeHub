package com.caij.codehub.presenter;

import com.caij.codehub.ui.listener.BaseUi;

/**
 * Created by Administrator on 2015/8/26.
 */
public interface BasePresent<T extends BaseUi> {

    public interface LoadType {
        int FIRSTLOAD = 1;
        int LOADMOER = 2;
        int REFRESH = 3;
    }

    public void attachUi(T ui);
    public void detachUi(T ui);
}
