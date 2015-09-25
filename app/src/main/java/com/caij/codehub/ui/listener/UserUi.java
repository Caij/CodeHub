package com.caij.codehub.ui.listener;

import com.android.volley.VolleyError;
import com.caij.codehub.bean.User;

/**
 * Created by Caij on 2015/9/18.
 */
public interface UserUi extends BaseUi{

    public void onGetUserInfoSuccess(User user);

}
