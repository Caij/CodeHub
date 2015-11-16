package com.caij.codehub.interactor;

import com.caij.codehub.bean.Repository;
import com.caij.codehub.ui.callback.UiCallBack;

/**
 * Created by Caij on 2015/9/19.
 */
public interface RepositoryInfoInteractor extends Interactor {

    public void getRepositoryInfo(String repositoryName, String owner, String token, Object requestTag, UiCallBack<Repository> uiCallBack);

}
