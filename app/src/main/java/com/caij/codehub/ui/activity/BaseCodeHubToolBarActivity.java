package com.caij.codehub.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import com.caij.codehub.R;

import butterknife.Bind;

/**
 * Created by Caij on 2015/9/19.
 */
public abstract class BaseCodeHubToolBarActivity extends BaseCodeHubActivity{

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSystemBarTintColor(getResources().getColor(R.color.theme_color));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_codehub_toolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    protected void setToolbarTitle(String title) {
        if (!TextUtils.isEmpty(title) && getSupportActionBar() != null) {
            if (title.length() > 12) {
                title = title.substring(0, 12) + "...";
            }
            getSupportActionBar().setTitle(title);
        }
    }

}
