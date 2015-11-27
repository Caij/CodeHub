package com.caij.codehub.present;

import com.caij.codehub.bean.Comment;
import com.caij.codehub.interactor.CommentActionInteractor;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.present.ui.CommentUi;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/17.
 */
public class CommentPresent extends Present<CommentUi>{

    private CommentActionInteractor mCommentActionInteractor;

    public CommentPresent(CommentUi ui) {
        super(ui);
        mCommentActionInteractor = InteractorFactory.newInteractorInstance(CommentActionInteractor.class);
    }

    public void createCommentForIssue(String comment, String owner, String repo, String num, String token) {
        mCommentActionInteractor.createCommentForIssue(comment, owner, repo, num, token, this, new DefaultInteractorCallback<Comment>(mUi) {
            @Override
            public void onError(int msgId) {
                CommentUi ui = mUi.get();
                if (ui != null) {
                    ui.showProgressBarLoading(false);
                    ui.showError(msgId);
                }
            }

            @Override
            public void onSuccess(Comment comment) {
                CommentUi ui = mUi.get();
                if (ui != null) {
                    ui.showProgressBarLoading(false);
                    ui.commentSuccess(comment);
                }
            }

            @Override
            public void onLoading() {
                CommentUi ui = mUi.get();
                if (ui != null) {
                    ui.showProgressBarLoading(true);
                }
            }
        });
    }

}
