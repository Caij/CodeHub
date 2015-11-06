package com.caij.codehub.presenter;

import com.caij.codehub.bean.Comment;
import com.caij.codehub.ui.callback.UiCallBack;

/**
 * Created by Caij on 2015/11/3.
 */
public interface CommentActionPresent extends Present {

    public void createCommentForIssue(String comment, String owner, String repo, String num, String token, Object requestTag, UiCallBack<Comment> uiCallBack);

}
