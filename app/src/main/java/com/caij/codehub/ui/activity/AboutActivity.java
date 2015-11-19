package com.caij.codehub.ui.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.caij.codehub.API;
import com.caij.codehub.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Caij on 2015/11/7.
 */
public class AboutActivity extends BaseCodeHubToolBarActivity {

    @Bind(R.id.tv_version)
    TextView mTvVersion;
    @Bind(R.id.tv_contact)
    TextView mTvContact;

    @Override
    protected int getAttachLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void handleIntent(Intent intent) {
        setTitle(getString(R.string.about));
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            mTvVersion.setText(getString(R.string.current_version) + "：" + String.valueOf(packageInfo.versionName));
        } catch (PackageManager.NameNotFoundException e) {
        }
        mTvContact.setText(R.string.my_email);
    }

    @OnClick(R.id.ll_open_source)
    public void onOpenSourceClick() {
        Intent intent = WebActivity.newIntent(this,  getString(R.string.app_name), API.PROJECT_README_URL);
        startActivity(intent);
    }

    @OnClick(R.id.ll_source_code)
     public void onSourceCodeClick() {
        Intent intent = WebActivity.newIntent(this, getString(R.string.app_name), API.PROJECT_URL);
        startActivity(intent);
    }

    @OnClick(R.id.ll_about_me)
    public void onAboutMeClick() {
        Intent intent = WebActivity.newIntent(this, getString(R.string.about_me), getString(R.string.about_me_github));
        startActivity(intent);
    }
}
