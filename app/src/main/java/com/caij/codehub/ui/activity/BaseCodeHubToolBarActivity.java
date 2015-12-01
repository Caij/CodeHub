package com.caij.codehub.ui.activity;

import android.content.Intent;
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
        setSystemBarTintColor(getResources().getColor(R.color.color_primary_dark));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handleIntent(getIntent());
    }

    protected abstract void handleIntent(Intent intent);

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_base_codehub_toolbar;
    }

    @Override
    protected int getContentContainerViewId() {
        return R.id.base_code_hub_container;
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

}
