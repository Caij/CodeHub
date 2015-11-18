package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.caij.codehub.API;
import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.present.RepositoryInfoPresent;
import com.caij.codehub.present.ui.RepositoryInfoUi;
import com.caij.codehub.utils.TimeUtils;
import com.caij.lib.utils.CheckValueUtil;
import com.caij.lib.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Caij on 2015/9/19.
 */
public class RepositoryInfoActivity extends BaseCodeHubToolBarActivity implements RepositoryInfoUi {

    @Bind(R.id.tv_repository_creater)
    TextView mRepositoryCreateTextView;
    @Bind(R.id.tv_repository_icon)
    TextView mRepositoryIcon;
    @Bind(R.id.tv_repository_name)
    TextView mRepositoryNameTextView;
    @Bind(R.id.tv_repository_update_time)
    TextView mRepositoryUpdateTimeTextView;
    @Bind(R.id.tv_repository_desc)
    TextView mRepositoryDescTextView;
    @Bind(R.id.tv_repository_language)
    TextView mRepositoryLanguageTextView;
    @Bind(R.id.tv_repository_create_time)
    TextView mRepositoryCreateTimeTextView;
    @Bind(R.id.tv_repository_star)
    TextView mRepositoryStarTextView;
    @Bind(R.id.tv_repository_fork)
    TextView mRepositoryForkTextView;

    private String mOwner;
    private String mRepo;
    private String mToken;
    private RepositoryInfoPresent mRepositoryInfoPresent;
    private Repository mRepository;
    private Menu mMenu;

    public static Intent newInstance(Activity activity, String owner, String repo) {
        CheckValueUtil.check(owner);
        CheckValueUtil.check(repo);
        Intent intent = new Intent(activity, RepositoryInfoActivity.class);
        intent.putExtra(Constant.USER_NAME, owner);
        intent.putExtra(Constant.REPO_NAME, repo);
        return intent;
    }

    @Override
    protected void handleIntent(Intent intent) {
        mContentContainer.setVisibility(View.GONE);
        mOwner = intent.getStringExtra(Constant.USER_NAME);
        mRepo = intent.getStringExtra(Constant.REPO_NAME);
        mToken = CodeHubPrefs.get().getToken();

        setTitle(mRepo);

        mRepositoryInfoPresent = new RepositoryInfoPresent(this);
        mRepositoryInfoPresent.getRepositoryInfo(mRepo, mOwner, mToken);
        mRepositoryInfoPresent.hasStarRepo(mOwner, mRepo, mToken);
    }

    @Override
    protected int getAttachLayoutId() {
        return R.layout.activity_repository_info;
    }

    @OnClick(R.id.ll_create)
    public void onCreaterClick() {
        Intent intent = UserInfoActivity.newIntent(this, mRepository.getOwner().getLogin());
        startActivity(intent);
    }

    @OnClick(R.id.ll_readme)
    public void onReadmeClick() {
        Intent intent = WebActivity.newIntent(this, "README", String.format(API.GITHUB_README, mOwner, mRepo));
        startActivity(intent);
    }

    @OnClick(R.id.ll_source)
    public void onSourceClick() {
        Intent intent = FileTreeActivity.newIntent(this, mOwner, mRepo, mRepository.getDefault_branch());
        startActivity(intent);
    }

    @OnClick(R.id.ll_website)
    public void onWebsiteClick() {
        String homepage = mRepository.getHomepage();
        if (!TextUtils.isEmpty(homepage)) {
            Intent intent = WebActivity.newIntent(this, mRepository.getName(), mRepository.getHomepage());
            startActivity(intent);
        }else {
            ToastUtil.show(this, R.string.not_have_website);
        }
    }

    @OnClick(R.id.ll_issue)
    public void onIssueClick() {
        Intent intent = RepoIssuesActivity.newIntent(this, mOwner, mRepo);
        startActivity(intent);
    }


    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mRepositoryInfoPresent.getRepositoryInfo(mRepo, mOwner, mToken);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.star) {
            if (item.getTitle().equals(getString(R.string.star))) {
                mRepositoryInfoPresent.starRepo(mOwner, mRepo, mToken);
            }else {
                mRepositoryInfoPresent.unstarRepo(mOwner, mRepo, mToken);
            }
            return true;
        }else if (id == R.id.fork) {
            mRepositoryInfoPresent.forkRepo(mOwner, mRepo, mToken);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getRepositoryInfoSuccess(Repository repository) {
        showContentContainer();
        mRepository = repository;
        mRepositoryNameTextView.setText(repository.getName());
        mRepositoryDescTextView.setText(repository.getDescription());
        mRepositoryStarTextView.setText(String.valueOf(repository.getStargazers_count()));
        mRepositoryForkTextView.setText(String.valueOf(repository.getForks_count()));
        if (repository.isFork()) {
            mRepositoryIcon.setText(getString(R.string.icon_fork));
        } else {
            mRepositoryIcon.setText(getString(R.string.icon_repo));
        }
        mRepositoryLanguageTextView.setText(repository.getLanguage());
        mRepositoryCreateTimeTextView.setText(TimeUtils.getStringTime(repository.getCreated_at()));
        mRepositoryUpdateTimeTextView.setText(getString(R.string.update) + " " + TimeUtils.getRelativeTime(repository.getUpdated_at()));
        mRepositoryCreateTextView.setText(repository.getOwner().getLogin());
    }

    @Override
    public void getStarStateSuccess(boolean isStar) {
        getMenuInflater().inflate(R.menu.menu_repository_info, mMenu);
        mMenu.findItem(R.id.star).setTitle(isStar ? getString(R.string.un_star) : getString(R.string.star));
    }

    @Override
    public void starSuccess() {
        ToastUtil.show(RepositoryInfoActivity.this, R.string.star_repo_success);
        mMenu.findItem(R.id.star).setTitle(getString(R.string.un_star));
    }

    @Override
    public void unstarSuccess() {
        ToastUtil.show(RepositoryInfoActivity.this, R.string.unstar_repo_success);
        mMenu.findItem(R.id.star).setTitle(getString(R.string.star));
    }

    @Override
    public void forkSuccess() {
        ToastUtil.show(RepositoryInfoActivity.this, R.string.fork_success);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRepositoryInfoPresent.onDeath();
    }

}
