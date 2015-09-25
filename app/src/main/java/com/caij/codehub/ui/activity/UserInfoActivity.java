package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.User;
import com.caij.codehub.dagger.DaggerUtils;
import com.caij.codehub.presenter.UserPresenter;
import com.caij.codehub.ui.listener.UserUi;
import com.caij.codehub.utils.TextTypeFaceUtils;
import com.caij.lib.utils.CheckValueUtil;
import com.caij.lib.utils.SPUtils;

import butterknife.Bind;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Caij on 2015/9/19.
 */
public class UserInfoActivity extends BaseCodeHubActivity<UserPresenter> implements UserUi {

    private String mToken;
    private String mUsername;

    public static Intent newIntent(Activity activity, String name) {
        CheckValueUtil.check(name);
        Intent intent = new Intent(activity, UserInfoActivity.class);
        intent.putExtra(Constant.USER_NAME, name);
        return intent;
    }

    public static final Typeface GITHUB_TYPE_FACE = TextTypeFaceUtils.getGithubTypeFace();

    @Bind(R.id.img_user_avatar)
    ImageView imgUserAvatar;
    @Bind(R.id.tv_user_followers)
    TextView tvUserFollowers;
    @Bind(R.id.tv_user_repository)
    TextView tvUserRepository;
    @Bind(R.id.tv_user_following)
    TextView tvUserFollowing;
    @Bind(R.id.tv_repository_location_icon)
    TextView tvRepositoryLocationIcon;
    @Bind(R.id.tv_repository_location)
    TextView tvRepositoryLocation;
    @Bind(R.id.tv_repository_email_icon)
    TextView tvRepositoryEmailIcon;
    @Bind(R.id.tv_repository_email)
    TextView tvRepositoryEmail;
    @Bind(R.id.tv_repository_blog_icon)
    TextView tvRepositoryBlogIcon;
    @Bind(R.id.tv_repository_blog)
    TextView tvRepositoryBlog;
    @Bind(R.id.tv_repository_company_icon)
    TextView tvRepositoryCompanyIcon;
    @Bind(R.id.tv_repository_company)
    TextView tvRepositoryCompany;
    @Bind(R.id.tv_repository_join_icon)
    TextView tvRepositoryJoinIcon;
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
        initTextViewTypeFace();
        mToken = SPUtils.get(Constant.USER_TOKEN, "");
        mUsername = getIntent().getStringExtra(Constant.USER_NAME);
        getSupportActionBar().setTitle(mUsername);
        mPresenter.getUserInfo(mToken, mUsername);
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

    private void initTextViewTypeFace() {
        tvRepositoryLocationIcon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryEmailIcon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryBlogIcon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryCompanyIcon.setTypeface(GITHUB_TYPE_FACE);
        tvRepositoryJoinIcon.setTypeface(GITHUB_TYPE_FACE);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public UserPresenter getPresenter() {
        return DaggerUtils.getPresenterComponent().provideUserPresenter();
    }

    @Override
    public void onGetUserInfoSuccess(User user) {
        handlerData(user);
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
}
