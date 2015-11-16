package com.caij.codehub.present.ui;

/**
 * Created by Caij on 2015/11/16.
 */
public interface BaseUi {

    public void onAuthError();

    public void showLoading();

    public void hideLoading();

    public void showError(int msgId);

    public void showErrorView();

}
