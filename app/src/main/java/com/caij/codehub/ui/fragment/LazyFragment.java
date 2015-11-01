package com.caij.codehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caij.codehub.presenter.BasePresent;
import com.caij.codehub.ui.activity.BaseCodeHubActivity;

/**
 * Created by Caij on 2015/9/18.
 */
public abstract class LazyFragment extends BaseCodeHubFragment{

    private boolean isFirstVisible = true;
    private boolean isPrepared = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            }
        }
    }

    private synchronized void initPrepare() {
        if (isPrepared) {
            onUserFirstVisible();
        } else {
            isPrepared = true;
        }
    }

    protected abstract void onUserFirstVisible();
}
