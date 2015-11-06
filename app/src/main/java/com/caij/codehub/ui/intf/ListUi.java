package com.caij.codehub.ui.intf;

import com.android.volley.VolleyError;
import com.caij.codehub.bean.Entity;

import java.util.List;

/**
 * Created by Caij on 2015/11/2.
 */
public interface ListUi<E extends Entity> extends BaseUi{

    public void onFirstLoadSuccess(List<E> entities);

    public void onRefreshSuccess(List<E> entities);

    public void onLoadMoreSuccess(List<E> entities);

    public void onRefreshError(VolleyError error);

    public void onLoadMoreError(VolleyError error);

    public void onFirstLoadError(VolleyError error);

    public void onComnLoading(int loadType);

}
