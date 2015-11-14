package com.caij.codehub.ui.activity;

import com.umeng.analytics.MobclickAgent;

/**
 * Author:  Caij
 * Email:   worldcaij@gmail.com
 * Date:    2015/11/13
 * Description:
 */
public class BaseCodeHubActivity extends BaseActivity{

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
