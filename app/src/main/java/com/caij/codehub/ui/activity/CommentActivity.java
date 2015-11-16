package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Comment;
import com.caij.codehub.interactor.CommentActionInteractor;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.ui.callback.DefaultUiCallBack;
import com.caij.lib.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Caij on 2015/11/3.
 */
public class CommentActivity extends BaseCodeHubToolBarActivity {

    @Bind(R.id.edit_comment)
    EditText mEditComment;

    private CommentActionInteractor mCommentActionInteractor;
    private String mRepo;
    private String mIssueNumber;
    private String mOwner;
    private String mToken;

    public static Intent newIntent(Activity activity,String owner, String repo, String issueNumber) {
        Intent intent = new Intent(activity, CommentActivity.class);
        intent.putExtra(Constant.REPO_NAME, repo);
        intent.putExtra(Constant.USER_NAME, owner);
        intent.putExtra(Constant.ISSUE_NUMBER, issueNumber);
        return intent;
    }

    @Override
    protected int getAttachLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.comment));
        mCommentActionInteractor = InteractorFactory.newPresentInstance(CommentActionInteractor.class);
        mRepo = getIntent().getStringExtra(Constant.REPO_NAME);
        mIssueNumber = getIntent().getStringExtra(Constant.ISSUE_NUMBER);
        mOwner = getIntent().getStringExtra(Constant.USER_NAME);
        mToken = CodeHubPrefs.get().getToken();
    }

    @OnClick(R.id.btn_comment)
    public void onCommentClick() {
        mCommentActionInteractor.createCommentForIssue(mEditComment.getText().toString(), mOwner, mRepo, mIssueNumber, mToken,
                getRequestTag(), new DefaultUiCallBack<Comment>(this) {
                    @Override
                    public void onSuccess(Comment comment) {
                        hideLoading();
                        ToastUtil.show(CommentActivity.this, R.string.comment_success);
                        Intent intent = new Intent();
                        intent.putExtra(Constant.COMMENT, comment);
                        setResult(RESULT_OK, intent);
                        finish();
                    }

                    @Override
                    public void onLoading() {
                        showLoading();
                    }

                    @Override
                    public void onDefaultError(VolleyError error) {
                        hideLoading();
                    }});
    }

}
