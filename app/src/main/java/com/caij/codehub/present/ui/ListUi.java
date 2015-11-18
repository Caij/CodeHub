package com.caij.codehub.present.ui;

import com.caij.codehub.bean.Entity;

import java.util.List;

/**
 * Created by Caij on 2015/11/2.
 */
public interface ListUi<E extends Entity> extends BaseUi {

    public void onFirstLoadSuccess(List<E> entities);

    public void onRefreshSuccess(List<E> entities);

    public void onLoadMoreSuccess(List<E> entities);

    public void onFirstLoadError(int msgId);

    public void onRefreshError(int msgId);

    public void onLoadMoreError(int msgId);
}
