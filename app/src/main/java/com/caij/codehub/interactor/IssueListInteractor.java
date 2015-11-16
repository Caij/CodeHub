package com.caij.codehub.interactor;

import com.caij.codehub.bean.Issue;
import com.caij.codehub.bean.Page;
import com.caij.codehub.ui.callback.UiCallBack;

import java.util.List;

/**
 * Created by Caij on 2015/11/3.
 */
public interface IssueListInteractor extends Interactor {

    public void getIssueList(String owner, String repoName, Page page, Object requestTag, UiCallBack<List<Issue>> uiCallBack);

}
