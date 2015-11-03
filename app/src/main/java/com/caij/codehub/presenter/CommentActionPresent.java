package com.caij.codehub.presenter;

import com.caij.codehub.ui.listener.CommentActionUi;

/**
 * Created by Caij on 2015/11/3.
 */
public interface CommentActionPresent extends Present<CommentActionUi> {

    public void createCommentForIssue(String comment, String owner, String repo, String num, String token);

}
