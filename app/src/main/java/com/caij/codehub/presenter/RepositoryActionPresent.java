package com.caij.codehub.presenter;

import com.caij.codehub.ui.listener.RepositoryActionUi;

/**
 * Created by Caij on 2015/10/31.
 */
public interface RepositoryActionPresent extends Present<RepositoryActionUi> {

    public static final int ACTION_TYPE_HAS_STAR = 1;
    public static final int ACTION_TYPE_STAR = 2;
    public static final int ACTION_TYPE_UNSTAR = 3;
    public static final int ACTION_TYPE_FORK = 4;

    public void hasStarRepo(String owner, String repo, String token);

    public void starRepo(String owner, String repo, String token);

    public void unstarRepo(String owner, String repo, String token);

    public void forkRepo(String owner, String repo, String token);
}
