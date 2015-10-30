package com.caij.codehub;

import android.app.Application;

import com.caij.codehub.dagger.DaggerUtils;
import com.caij.codehub.dagger.component.DaggerPresenterComponent;
import com.caij.codehub.dagger.modle.PresenterModel;
import com.caij.codehub.utils.OkHttpClientProvider;
import com.caij.codehub.utils.TextTypeFaceUtils;
import com.caij.lib.utils.LogUtil;
import com.caij.lib.utils.SPUtils;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.volley.stack.OkHttpStack;


/**
 * Created by Caij on 2015/8/25.
 */
public class CodeHubApplication extends Application{

    private static final String SP_FILE_NAME = "profile";

    private static final int VOLLEY_DISK_MAX_SIZE = 1024 * 1024 * 5;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerUtils.initPresenterComponent(DaggerPresenterComponent.builder().presenterModel(new PresenterModel()).build());
        VolleyUtil.init(this, new OkHttpStack(OkHttpClientProvider.getOkHttpClient()), VOLLEY_DISK_MAX_SIZE);
        SPUtils.init(this, SP_FILE_NAME);
    }

}
