package com.caij.codehub.presenter;

import com.caij.codehub.ui.listener.CommentsUi;

/**
 * Created by Caij on 2015/10/31.
 */
public interface CommentsPresent extends Present<CommentsUi> {

    public void getIssuesComments(int loadType, String owner, String repo, String issueNumber);
}
