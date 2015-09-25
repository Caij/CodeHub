package com.caij.codehub.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.presenter.BasePresent;
import com.caij.codehub.ui.listener.BaseUi;
import com.caij.lib.utils.AppManager;
import com.caij.lib.utils.SPUtils;
import com.caij.lib.utils.ToastUtil;
import com.caij.lib.volley.request.JsonParseError;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/9/19.
 */
public abstract class BaseCodeHubActivity<P extends BasePresent> extends BaseActivity implements BaseUi {

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Nullable
    @Bind(R.id.pb_content_loading)
    ProgressBar pbLoading;

    @Nullable
    @Bind(R.id.ll_load_error)
    LinearLayout llLoadError;

    @Nullable
    @Bind(R.id.btn_refresh)
    Button btnRefresh;

    ViewGroup content;

    protected P mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSystemBarTintColor(getResources().getColor(R.color.theme_color));
        setContentView(R.layout.activity_base_toolbar);
        content = (ViewGroup) findViewById(R.id.base_code_hub_content);
        getLayoutInflater().inflate(getContentLayoutId(), content, true);
        ButterKnife.bind(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onReFreshBtnClick(view);
            }
        });

        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachUi(this);
        }
    }

    protected abstract int getContentLayoutId();

    public abstract P getPresenter();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (mPresenter != null) {
            mPresenter.detachUi(this);
        }
    }

    @Override
    public void showError(int type, VolleyError error) {
        if (llLoadError != null && type == BasePresent.LoadType.FIRSTLOAD) {
            llLoadError.setVisibility(View.VISIBLE);
        }
        processVolleyError(error);
    }

    @Override
    public void showLoading(int loadType) {
        if (pbLoading != null && loadType == BasePresent.LoadType.FIRSTLOAD) {
            pbLoading.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading(int loadType) {
        if (pbLoading != null && loadType == BasePresent.LoadType.FIRSTLOAD) {
            pbLoading.setVisibility(View.GONE);
        }
    }

    public void onReFreshBtnClick(View view) {
        hideError();
    }

    protected  void hideError(){
        if (llLoadError != null) {
            llLoadError.setVisibility(View.GONE);
        }
    }

    protected void processVolleyError(VolleyError error) {
        if (error instanceof ServerError || error instanceof JsonParseError) {
            ToastUtil.show(this, R.string.server_error_hint);
        }else if(error instanceof NetworkError || error instanceof TimeoutError) {
            ToastUtil.show(this, R.string.network_error_hint);
        }else if (error instanceof AuthFailureError) {
            AppManager.getInstance().finishAllActivity();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            //clear cahce data
            SPUtils.save(Constant.USER_TOKEN, "");
            SPUtils.save(Constant.USER_INFO, "");
            SPUtils.save(Constant.USER_NAME, "");
            ToastUtil.show(this, R.string.account_error_hint);
        }else {
            ToastUtil.show(this, R.string.data_load_error_hint);
        }
    }

    protected void setToolbarTitle(String title) {
        if (title.length() > 12) {
            title = title.substring(0, 12) + "...";
        }
        getSupportActionBar().setTitle(title);
    }
}
