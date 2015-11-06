package com.caij.codehub.presenter;


import com.caij.codehub.bean.Issue;
import com.caij.codehub.ui.callback.UiCallBack;

/**
 * Created by Caij on 2015/10/31.
 */
public interface IssuePresent extends Present {

    public void getIssue(String owner, String repo, String issueNumber, Object requestTag, UiCallBack<Issue> uiCallBack);
}
