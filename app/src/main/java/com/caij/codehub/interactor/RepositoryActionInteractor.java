package com.caij.codehub.interactor;

import com.android.volley.NetworkResponse;

/**
 * Created by Caij on 2015/10/31.
 */
public interface RepositoryActionInteractor extends Interactor {

    public void hasStarRepo(String owner, String repo, String token, Object requestTag, InteractorCallBack<Boolean> interactorCallBack);

    public void starRepo(String owner, String repo, String token, Object requestTag, InteractorCallBack<NetworkResponse> interactorCallBack);

    public void unstarRepo(String owner, String repo, String token, Object requestTag, InteractorCallBack<NetworkResponse> interactorCallBack);

    public void forkRepo(String owner, String repo, String token, Object requestTag, InteractorCallBack<NetworkResponse> interactorCallBack);
}
