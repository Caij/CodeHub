package com.caij.codehub.present;

import com.caij.codehub.bean.Comment;
import com.caij.codehub.interactor.CommentsInteractor;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.present.ui.ListUi;

import java.util.List;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/16.
 */
public class CommentsPresent extends ListPresent<ListUi<Comment>, Comment>{

    private CommentsInteractor mCommentsInteractor;

    public CommentsPresent(ListUi<Comment> ui) {
        super(ui);
        mCommentsInteractor = InteractorFactory.newInteractorInstance(CommentsInteractor.class);
    }

    public void getIssuesComments(String owner, String repo, String issueNumber) {
        mCommentsInteractor.getIssuesComments(owner, repo, issueNumber, this, new DefaultInteractorCallback<List<Comment>>(mUi) {
            @Override
            public void onError(int msgId) {
                defaultDealWithError(msgId, LoadType.FIRST);
            }

            @Override
            public void onSuccess(List<Comment> comments) {
                defaultDealWithSuccess(comments, LoadType.FIRST);
            }

            @Override
            public void onLoading() {
                defaultDealWithLoading(LoadType.FIRST);
            }
        });
    }

}
