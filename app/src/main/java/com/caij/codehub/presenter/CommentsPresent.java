package com.caij.codehub.presenter;

import com.caij.codehub.ui.listener.CommentsUi;

/**
 * Created by Caij on 2015/10/31.
 */
public interface CommentsPresent extends BasePresent<CommentsUi>{

    public void getIssuesComments(String repo, String issueNumber);
}
