package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.caij.codehub.CodeHubApplication;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Comment;
import com.caij.codehub.presenter.CommentActionPresent;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.ui.listener.CommentActionUi;
import com.caij.lib.utils.ToastUtil;

import butterknife.Bind;

/**
 * Created by Caij on 2015/11/3.
 */
public class CommentActivity extends BaseCodeHubActivity implements CommentActionUi {

    @Bind(R.id.edit_comment)
    EditText mEditComment;

    private CommentActionPresent mCommentActionPresent;
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
    protected int getContentLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCommentActionPresent = PresenterFactory.newPresentInstance(CommentActionPresent.class, CommentActionUi.class, this);
        mRepo = getIntent().getStringExtra(Constant.REPO_NAME);
        mIssueNumber = getIntent().getStringExtra(Constant.ISSUE_NUMBER);
        mOwner = getIntent().getStringExtra(Constant.USER_NAME);
        mToken = CodeHubApplication.getToken();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_issue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.comment) {
            mCommentActionPresent.createCommentForIssue(mEditComment.getText().toString(), mOwner, mRepo, mIssueNumber, mToken);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCommentSuccess(Comment comment) {
        ToastUtil.show(this, R.string.comment_success);
        Intent intent = new Intent();
        intent.putExtra(Constant.COMMENT, comment);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onCommentError(VolleyError error) {
        processVolleyError(error);
    }

    @Override
    public void onCommentLoading() {
        showLoading();
    }

    @Override
    public void onCommentLoaded() {
        hideLoading();
    }
}
