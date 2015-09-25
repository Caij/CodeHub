package com.caij.codehub.ui.listener;

import com.android.volley.VolleyError;
import com.caij.codehub.bean.Token;

/**
 * Created by Caij on 2015/8/25.
 */
public interface LoginUi extends BaseUi{
    public void onLoginSuccess(Token token);
}
