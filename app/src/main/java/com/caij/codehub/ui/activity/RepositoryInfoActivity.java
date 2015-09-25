package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.dagger.DaggerUtils;
import com.caij.codehub.presenter.RepositoryInfoPresenter;
import com.caij.codehub.ui.listener.RepositoryInfoUi;
import com.caij.codehub.utils.TextTypeFaceUtils;
import com.caij.lib.utils.CheckValueUtil;
import com.caij.lib.utils.SPUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Caij on 2015/9/19.
 */
public class RepositoryInfoActivity extends BaseCodeHubActivity<RepositoryInfoPresenter> implements RepositoryInfoUi {

    public static final Typeface GITHUB_TYPE_FACE = TextTypeFaceUtils.getGithubTypeFace();
    private String owner;
    private String repo;

    public static Intent newInstance(Activity activity, String owner, String repo) {
        CheckValueUtil.check(owner);
        CheckValueUtil.check(repo);
        Intent intent = new Intent(activity, RepositoryInfoActivity.class);
        intent.putExtra(Constant.USER_NAME, owner);
        intent.putExtra(Constant.REPO_NAME, repo);
        return intent;
    }
    @Bind(R.id.tv_repository_create)
    TextView tvRepositoryCreate;
    @Bind(R.id.tv_repository_icon)
    TextView tvRepositoryIcon;
    @Bind(R.id.tv_repository_name)
    TextView tvRepositoryName;
    @Bind(R.id.tv_repository_update_time)
    TextView tvRepositoryUpdateTime;
    @Bind(R.id.tv_repository_desc)
    TextView tvRepositoryDesc;
    @Bind(R.id.tv_repository_language)
    TextView tvRepositoryLanguage;
    @Bind(R.id.tv_repository_create_time)
    TextView tvRepositoryCreateTime;
    @Bind(R.id.tv_repository_language_icon)
    TextView tvRepositoryLanguageIcon;
    @Bind(R.id.tv_repository_create_time_icon)
    TextView tvRepositoryCreateTimeIcon;
    @Bind(R.id.tv_repository_star_icon)
    TextView tvRepositoryStarIcon;
    @Bind(R.id.tv_repository_star)
    TextView tvRepositoryStar;
    @Bind(R.id.tv_repository_create_fork_icon)
    TextView tvRepositoryCreateForkIcon;
    @Bind(R.id.tv_repository_fork)
    TextView tvRepositoryFork;
    @Bind(R.id.tv_repository_owner_icon)
    TextView tvRepositoryOwnerIcon;
    @Bind(R.id.tv_repository_next_icon)
    TextView tvRepositoryNextIcon;
    @Bind(R.id.tv_repository_issues_icon)
    TextView tvRepositoryIssuesIcon;
    @Bind(R.id.tv_repository_next2_icon)
    TextView tvRepositoryNext2Icon;
    @Bind(R.id.tv_repository_readme_icon)
    TextView tvRepositoryReadmeIcon;
    @Bind(R.id.tv_repository_next3_icon)
    TextView tvRepositoryNext3Icon;
    @Bind(R.id.tv_repository_website_icon)
    TextView tvRepositoryWebsiteIcon;
    @Bind(R.id.tv_repository_next4_icon)
    TextView tvRepositoryNext4Icon;


    private Repository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content.setVisibility(View.GONE);
        initTextViewTypeFace();

        Intent intent = getIntent();
        owner = intent.getStringExtra(Constant.USER_NAME);
        repo = intent.getStringExtra(Constant.REPO_NAME);

        setToolbarTitle(repo);

        mPresenter.getRepositoryInfo(repo, owner, SPUtils.get(Constant.USER_TOKEN, ""));
    }

    private void initTextViewTypeFace() {
        tvRepositoryIcon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryLanguageIcon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryStarIcon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryCreateForkIcon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryCreateTimeIcon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryOwnerIcon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryNextIcon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryIssuesIcon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryNext2Icon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryReadmeIcon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryNext4Icon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryWebsiteIcon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryNext3Icon.setTypeface(GITHUB_TYPE_FACE);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_repository_info;
    }

    @Override
    public RepositoryInfoPresenter getPresenter() {
        return DaggerUtils.getPresenterComponent().provideRepositoryInfoPresenter();
    }

    @Override
    public void onGetRepositoryInfoSuccess(Repository repository) {
        mRepository = repository;
        content.setVisibility(View.VISIBLE);
        tvRepositoryName.setText(repository.getName());
        tvRepositoryDesc.setText(repository.getDescription());
        tvRepositoryStar.setText(String.valueOf(repository.getStargazers_count()));
        tvRepositoryFork.setText(String.valueOf(repository.getForks_count()));
        if (repository.isFork()) {
            tvRepositoryIcon.setText(getString(R.string.icon_fork));
        } else {
            tvRepositoryIcon.setText(getString(R.string.icon_repo));
        }
        tvRepositoryLanguage.setText(repository.getLanguage());
        tvRepositoryCreateTime.setText(repository.getCreated_at().substring(0, 10));
        tvRepositoryUpdateTime.setText("Update " + repository.getUpdated_at().substring(0, 10));
        tvRepositoryCreate.setText(repository.getOwner().getLogin());
    }


    @OnClick(R.id.ll_create)
    public void onCreateClick() {
        Intent intent = UserInfoActivity.newIntent(this, mRepository.getOwner().getLogin());
        startActivity(intent);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mPresenter.getRepositoryInfo(repo, owner, SPUtils.get(Constant.USER_TOKEN, ""));
    }
}
