package com.caij.codehub.presenter;

import com.caij.codehub.ui.listener.RepositoryActionUi;

/**
 * Created by Caij on 2015/10/31.
 */
public interface RepositoryActionPresent extends BasePresent<RepositoryActionUi>{

    public void hasStarRepo(String owner, String repo, String token);

    public void starRepo(String owner, String repo, String token);

    public void unstarRepo(String owner, String repo, String token);

    public void forkRepo(String owner, String repo, String token);
}
