package com.caij.codehub.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.caij.codehub.bean.User;
import com.caij.codehub.ui.activity.UserInfoActivity;

/**
 * Author:  Caij
 * Email:   worldcaij@gmail.com
 * Date:    2015/11/19
 * Description:
 */
public class AvatarOnClickListener implements View.OnClickListener{

    private final Activity activity;
    private User user;

    public AvatarOnClickListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        if (user != null) {
            Intent intent = UserInfoActivity.newIntent(activity, user.getLogin());
            activity.startActivity(intent);
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
