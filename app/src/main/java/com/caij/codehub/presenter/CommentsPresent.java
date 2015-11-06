package com.caij.codehub.presenter;

import com.caij.codehub.bean.Comment;
import com.caij.codehub.ui.callback.UiCallBack;

import java.util.List;

/**
 * Created by Caij on 2015/10/31.
 */
public interface CommentsPresent extends Present {

    public void getIssuesComments(String owner, String repo, String issueNumber, Object requestTag, UiCallBack<List<Comment>> uiCallBack);
}
