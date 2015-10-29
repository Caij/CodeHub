package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.presenter.BasePresent;
import com.caij.lib.utils.CheckValueUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/10/29.
 */
public class WebActivity extends BaseCodeHubActivity {

    public static Intent newInstance(Activity activity, String url) {
        CheckValueUtil.check(url);
        Intent intent = new Intent(activity, WebActivity.class);
        intent.putExtra(Constant.URL, url);
        return intent;
    }

    @Bind(R.id.webview)
    WebView webview;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public BasePresent getPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().getUserAgentString();
        webview.getSettings().setSupportZoom(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webview.setWebChromeClient(new WebChromeClient());

        //自适应屏幕
        webview.getSettings().setSupportMultipleWindows(true);
        webview.getSettings().setUseWideViewPort(true);

        webview.getSettings().setLoadWithOverviewMode(true);
        String url = getIntent().getStringExtra(Constant.URL);
        webview.loadUrl(url);
    }
}
