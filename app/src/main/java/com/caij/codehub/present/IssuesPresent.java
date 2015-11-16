package com.caij.codehub.present;

import com.caij.codehub.bean.Issue;
import com.caij.codehub.bean.Page;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.interactor.IssueListInteractor;
import com.caij.codehub.present.ui.ListUi;

import java.util.List;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/16.
 */
public class IssuesPresent extends ListPresent<ListUi<Issue>, Issue>{

    private IssueListInteractor mIssueListInteractor;

    public IssuesPresent(ListUi<Issue> ui) {
        super(ui);
        mIssueListInteractor = InteractorFactory.newPresentInstance(IssueListInteractor.class);
    }

    public void getIssueList(final LoadType loadType, String owner, String repoName, Page page) {
        mIssueListInteractor.getIssueList(owner, repoName, page, this, new DefaultInteractorCallback<List<Issue>>(mUi) {
            @Override
            public void onError(int msgId) {
                defaultDealWithError(msgId, loadType);
            }

            @Override
            public void onSuccess(List<Issue> issues) {
                defaultDealWithSuccess(issues, loadType);
            }

            @Override
            public void onLoading() {
                defaultDealWithLoading(loadType);
            }
        });
    }
}
