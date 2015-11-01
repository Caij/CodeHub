package com.caij.codehub.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.caij.codehub.CodeHubApplication;
import com.caij.codehub.R;
import com.caij.lib.utils.AppManager;
import com.caij.lib.utils.SystemBarTintManager;


/**
 * Created by Caij on 2015/8/24.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected int mScreenWidth = -1;
    protected int mScreenHeight = - 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().add(this);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void setSystemBarTintColor(int color) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {

            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);

            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            if (color != 0) {
                mTintManager.setStatusBarTintEnabled(true);
                mTintManager.setStatusBarTintColor(color);
            }
        }else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setStatusBarColor(color);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void setFullScreen() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    /**
     * 获取状态栏的高度
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().remove(this);
    }

    public void switchContent(Fragment from, Fragment to, int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {    // 先判断是否被add过
            transaction.hide(from).add(id, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
        }
    }
}
