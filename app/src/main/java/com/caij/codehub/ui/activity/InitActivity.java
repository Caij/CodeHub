package com.caij.codehub.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.text.TextUtils;

import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.lib.utils.SPUtils;

public class InitActivity extends BaseCodeHubActivity {

    public static final long SKIP_DELAY_TIME = 3000;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        mHandler = new Handler();
    }

    @Override
    protected int getAttachLayoutId() {
        return R.layout.activity_init;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (TextUtils.isEmpty(CodeHubPrefs.get().getToken())) {
                    intent = new Intent(InitActivity.this, LoginActivity.class);
                } else {
                    intent = new Intent(InitActivity.this, MainActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, SKIP_DELAY_TIME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacksAndMessages(null);
    }
}
