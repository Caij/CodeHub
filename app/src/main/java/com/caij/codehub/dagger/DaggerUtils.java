package com.caij.codehub.dagger;

import com.caij.codehub.dagger.component.PresenterComponent;

/**
 * Created by Caij on 2015/9/19.
 */
public class DaggerUtils {
    private static PresenterComponent mPresenterComponent;

    public static void initPresenterComponent(PresenterComponent presenterComponent) {
        mPresenterComponent = presenterComponent;
    }

    public static PresenterComponent getPresenterComponent() {
        return mPresenterComponent;
    }
}
