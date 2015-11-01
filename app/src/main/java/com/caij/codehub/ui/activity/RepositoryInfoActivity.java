package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.presenter.RepositoryActionPresent;
import com.caij.codehub.presenter.RepositoryInfoPresenter;
import com.caij.codehub.ui.listener.RepositoryActionUi;
import com.caij.codehub.ui.listener.RepositoryInfoUi;
import com.caij.codehub.utils.TimeUtils;
import com.caij.lib.utils.CheckValueUtil;
import com.caij.lib.utils.SPUtils;
import com.caij.lib.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Caij on 2015/9/19.
 */
public class RepositoryInfoActivity extends BaseCodeHubActivity implements RepositoryInfoUi, RepositoryActionUi {

    private String owner;
    private String repo;
    private RepositoryInfoPresenter mPresenter;
    private String token;
    private RepositoryActionPresent mRepositoryActionPresent;

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
    @Bind(R.id.tv_repository_star)
    TextView tvRepositoryStar;
    @Bind(R.id.tv_repository_fork)
    TextView tvRepositoryFork;


    private Repository mRepository;

    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content.setVisibility(View.GONE);

        Intent intent = getIntent();
        owner = intent.getStringExtra(Constant.USER_NAME);
        repo = intent.getStringExtra(Constant.REPO_NAME);
        token = SPUtils.get(Constant.USER_TOKEN, "");

        setToolbarTitle(repo);

        mPresenter = PresenterFactory.newPresentInstance(RepositoryInfoPresenter.class, RepositoryInfoUi.class, this);
        mPresenter.getRepositoryInfo(repo, owner, SPUtils.get(Constant.USER_TOKEN, ""));

        mRepositoryActionPresent = PresenterFactory.newPresentInstance(RepositoryActionPresent.class,
                RepositoryActionUi.class, this);
        mRepositoryActionPresent.hasStarRepo(owner, repo, token);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_repository_info;
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
        tvRepositoryCreateTime.setText(TimeUtils.getRelativeTime(repository.getCreated_at()));
        tvRepositoryUpdateTime.setText("Update " + TimeUtils.getRelativeTime(repository.getUpdated_at()));
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.star) {
            if (item.getTitle().equals(getString(R.string.star))) {
                mRepositoryActionPresent.starRepo(owner, repo, token);
            }else {
                mRepositoryActionPresent.unstarRepo(owner, repo, token);
            }
            return true;
        }else if (id == R.id.fork) {
            mRepositoryActionPresent.forkRepo(owner, repo, token);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckStarStateSuccess(boolean isStar) {
        getMenuInflater().inflate(R.menu.menu_repository_info, mMenu);
        mMenu.findItem(R.id.star).setTitle(isStar ? getString(R.string.un_star) : getString(R.string.star));
    }

    @Override
    public void onStarRepoSuccess() {
        ToastUtil.show(this, R.string.star_repo_success);
        mMenu.findItem(R.id.star).setTitle(getString(R.string.un_star));
    }

    @Override
    public void onStarRepoError(VolleyError error) {
        ToastUtil.show(this, R.string.star_repo_error);
    }

    @Override
    public void onUnstarRepoSuccess() {
        ToastUtil.show(this, R.string.unstar_repo_success);
        mMenu.findItem(R.id.star).setTitle(getString(R.string.star));
    }

    @Override
    public void onUnstarRepoError(VolleyError error) {
        ToastUtil.show(this, R.string.unstar_repo_error);
    }

    @Override
    public void onForkRepoSuccess() {
        ToastUtil.show(this, R.string.fork_success);
    }

    @Override
    public void onForkRepoError(VolleyError error) {
        ToastUtil.show(this, R.string.fork_error);
    }

}
