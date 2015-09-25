package com.caij.codehub.ui.listener;

import com.android.volley.VolleyError;

/**
 * Created by Caij on 2015/8/25.
 */
public interface BaseUi{

    public void showError(int type, VolleyError error);

    public void showLoading(int loadType);

    public void hideLoading(int loadType);

}
