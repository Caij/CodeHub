package com.caij.codehub.interactor;


import com.caij.codehub.bean.Issue;

/**
 * Created by Caij on 2015/10/31.
 */
public interface IssueInteractor extends Interactor {

    public void getIssue(String owner, String repo, String issueNumber, Object requestTag, UiCallBack<Issue> uiCallBack);
}
