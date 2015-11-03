package com.caij.codehub.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.caij.codehub.R;
import com.caij.codehub.utils.FileUtil;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by Caij on 2015/11/3.
 */
public class SettingActivity extends BaseCodeHubActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle("Setting");
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_setting;
    }

    @OnClick(R.id.rl_login_out)
    public void onLoginOutClick() {

    }
}
