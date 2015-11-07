package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.lib.utils.CheckValueUtil;
import com.caij.lib.utils.LogUtil;

import butterknife.Bind;

/**
 * Created by Caij on 2015/10/29.
 */
public class WebActivity extends BaseCodeHubActivity {

    private static final String TAG = "WebActivity";

    @Bind(R.id.webview)
    WebView mWebview;

    public static Intent newIntent(Activity activity, String title, String url) {
        CheckValueUtil.check(url);
        Intent intent = new Intent(activity, WebActivity.class);
        intent.putExtra(Constant.URL, url);
        intent.putExtra(Constant.TITLE, title);
        return intent;
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = getIntent().getStringExtra(Constant.TITLE);
        setTitle(title);

        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().getUserAgentString();
        mWebview.getSettings().setSupportZoom(true);
        mWebview.setWebViewClient(new HubWebClient());
        mWebview.setWebChromeClient(new WebChromeClient());

        //自适应屏幕
        mWebview.getSettings().setSupportMultipleWindows(true);
        mWebview.getSettings().setUseWideViewPort(true);

        mWebview.getSettings().setLoadWithOverviewMode(true);
        String url = getIntent().getStringExtra(Constant.URL);
        mWebview.loadUrl(url);

        LogUtil.d(TAG, url);
    }

    @Override
    public void onBackPressed() {
        if (mWebview.canGoBack()) {
            mWebview.goBack();
            return;
        }
        super.onBackPressed();
    }

    private class HubWebClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            showLoading();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            hideLoading();
        }
    }

}
