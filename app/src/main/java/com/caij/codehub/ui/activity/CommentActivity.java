package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;

import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Comment;
import com.caij.codehub.present.CommentPresent;
import com.caij.codehub.present.ui.CommentUi;
import com.caij.util.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Caij on 2015/11/3.
 */
public class CommentActivity extends BaseCodeHubToolBarActivity implements CommentUi {

    @Bind(R.id.edit_comment)
    EditText mEditComment;

    private CommentPresent mCommentPresent;
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
    protected void handleIntent(Intent intent) {
        setTitle(getString(R.string.comment));
        mCommentPresent = new CommentPresent(this);
        mRepo = intent.getStringExtra(Constant.REPO_NAME);
        mIssueNumber = intent.getStringExtra(Constant.ISSUE_NUMBER);
        mOwner = intent.getStringExtra(Constant.USER_NAME);
        mToken = CodeHubPrefs.get().getToken();
    }

    @OnClick(R.id.btn_comment)
    public void onCommentClick() {
        mCommentPresent.createCommentForIssue(mEditComment.getText().toString(), mOwner, mRepo, mIssueNumber, mToken);
    }

    @Override
    public void commentSuccess(Comment comment) {
        ToastUtil.show(CommentActivity.this, R.string.comment_success);
        Intent intent = new Intent();
        intent.putExtra(Constant.COMMENT, comment);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        mCommentPresent.onDeath();
        super.onDestroy();
    }
}
