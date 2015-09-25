package com.caij.codehub.presenter;

import com.caij.codehub.ui.listener.RepositoryInfoUi;

/**
 * Created by Caij on 2015/9/19.
 */
public interface RepositoryInfoPresenter extends BasePresent<RepositoryInfoUi>{

    public void getRepositoryInfo(String repositoryName, String owner, String token);
}
