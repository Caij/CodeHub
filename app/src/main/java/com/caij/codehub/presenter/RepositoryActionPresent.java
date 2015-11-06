package com.caij.codehub.presenter;

import com.android.volley.NetworkResponse;
import com.caij.codehub.ui.callback.UiCallBack;

/**
 * Created by Caij on 2015/10/31.
 */
public interface RepositoryActionPresent extends Present {

    public void hasStarRepo(String owner, String repo, String token, Object requestTag, UiCallBack<Boolean> uiCallBack);

    public void starRepo(String owner, String repo, String token, Object requestTag, UiCallBack<NetworkResponse> uiCallBack);

    public void unstarRepo(String owner, String repo, String token, Object requestTag, UiCallBack<NetworkResponse> uiCallBack);

    public void forkRepo(String owner, String repo, String token, Object requestTag, UiCallBack<NetworkResponse> uiCallBack);
}
