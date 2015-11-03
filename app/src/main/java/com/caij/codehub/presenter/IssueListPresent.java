package com.caij.codehub.presenter;

import com.caij.codehub.bean.Page;
import com.caij.codehub.ui.listener.IssueUi;

/**
 * Created by Caij on 2015/11/3.
 */
public interface IssueListPresent extends Present<IssueUi> {

    public void getIssueList(int loadType, String owner, String repoName, Page page);

}
