package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Comment;
import com.caij.codehub.bean.User;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.presenter.UserFollowPresent;
import com.caij.codehub.presenter.UserPresenter;
import com.caij.codehub.ui.listener.UserFollowUi;
import com.caij.codehub.ui.listener.UserUi;
import com.caij.codehub.widgets.GithubTypeFaceTextView;
import com.caij.lib.utils.CheckValueUtil;
import com.caij.lib.utils.SPUtils;
import com.caij.lib.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Caij on 2015/9/19.
 */
public class UserInfoActivity extends BaseCodeHubActivity implements UserUi, UserFollowUi {


    private String mToken;
    private String mUsername;
    private UserPresenter mPresenter;
    private Menu mMenu;
    private UserFollowPresent userFollowPresent;

    public static Intent newIntent(Activity activity, String name) {
        CheckValueUtil.check(name);
        Intent intent = new Intent(activity, UserInfoActivity.class);
        intent.putExtra(Constant.USER_NAME, name);
        return intent;
    }
    @Bind(R.id.img_user_avatar)
    ImageView imgUserAvatar;
    @Bind(R.id.tv_user_followers)
    TextView tvUserFollowers;
    @Bind(R.id.tv_user_repository)
    TextView tvUserRepository;
    @Bind(R.id.tv_user_following)
    TextView tvUserFollowing;
    @Bind(R.id.tv_repository_location)
    TextView tvRepositoryLocation;
    @Bind(R.id.tv_repository_email)
    TextView tvRepositoryEmail;
    @Bind(R.id.tv_repository_blog)
    TextView tvRepositoryBlog;
    @Bind(R.id.tv_repository_company)
    TextView tvRepositoryCompany;
    @Bind(R.id.tv_repository_join)
    TextView tvRepositoryJoin;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.tv_user_nickname)
    TextView tvUserNickname;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content.setVisibility(View.GONE);
        mToken = SPUtils.get(Constant.USER_TOKEN, "");
        mUsername = getIntent().getStringExtra(Constant.USER_NAME);
        getSupportActionBar().setTitle(mUsername);
        mPresenter = PresenterFactory.newPresentInstance(UserPresenter.class, UserUi.class, this);
        mPresenter.getUserInfo(mToken, mUsername);

        userFollowPresent = PresenterFactory.newPresentInstance(UserFollowPresent.class,
                UserFollowUi.class, this);
        userFollowPresent.checkFollowState(mToken, mUsername);
    }

    protected void handlerData(User user) {
        mUser = user;
        content.setVisibility(View.VISIBLE);
        Glide.with(this).load(user.getAvatar_url()).placeholder(R.drawable.default_circle_head_image).
                bitmapTransform(new CropCircleTransformation(this)).into(imgUserAvatar);
        tvUserFollowers.setText(String.valueOf(user.getFollowers()));
        tvUserFollowing.setText(String.valueOf(user.getFollowing()));
        tvRepositoryLocation.setText(user.getLocation());
        tvRepositoryCompany.setText(user.getCompany());
        tvRepositoryEmail.setText(user.getEmail());
        tvRepositoryBlog.setText(user.getBlog());
        tvRepositoryJoin.setText(user.getCreated_at());
        tvUserName.setText(user.getLogin());
        tvUserNickname.setText(user.getName());
        tvUserRepository.setText(String.valueOf(user.getPublic_repos()));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public void onGetUserInfoSuccess(User user) {
        handlerData(user);
    }

    @Override
    public void onCheckFollowInfoSuccess(boolean isFollow) {
        getMenuInflater().inflate(R.menu.menu_user, mMenu);
        mMenu.findItem(R.id.follow).setTitle(isFollow ? getString(R.string.unfollow) : getString(R.string.follow));
    }

    @Override
    public void onFollowSuccess() {
        ToastUtil.show(this, R.string.follow_success);
        mMenu.findItem(R.id.follow).setTitle(R.string.unfollow);
    }

    @Override
    public void onFollowError() {
        ToastUtil.show(this, R.string.follow_erroe);
    }

    @Override
    public void onUnfollowSuccess() {
        ToastUtil.show(this, R.string.unfollow_success);
        mMenu.findItem(R.id.follow).setTitle(R.string.follow);
    }

    @Override
    public void onUnfollowError() {
        ToastUtil.show(this, R.string.unfollow_erroe);
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

        if (id == R.id.follow) {
            if (item.getTitle().equals(getString(R.string.unfollow))) {
                userFollowPresent.unfollowUser(mToken, mUsername);
            }else {
                userFollowPresent.followUser(mToken, mUsername);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mPresenter.getUserInfo(mToken, mUsername);
    }

    @OnClick(R.id.ll_user_followers)
    public void onFollowersClick() {
        Intent intent = FollowersActivity.newIntent(this, mUser.getLogin());
        startActivity(intent);
    }

    @OnClick(R.id.ll_user_following)
    public void onFollowingClick() {
        Intent intent = FollowingActivity.newIntent(this, mUser.getLogin());
        startActivity(intent);
    }

    @OnClick(R.id.ll_user_repository)
    public void onRepositoryClick() {
        Intent intent = UserRepositoriesActivity.newIntent(this, mUser.getLogin());
        startActivity(intent);
    }

    @OnClick(R.id.ll_blog)
    public void onBlogClick() {
        String blogUrl = mUser.getBlog();
        if (!TextUtils.isEmpty(blogUrl)) {
            Intent intent = WebActivity.newInstance(this, blogUrl);
            startActivity(intent);
        }
    }
}
