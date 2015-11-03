package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.caij.codehub.CodeHubApplication;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.User;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.presenter.FollowActionPresent;
import com.caij.codehub.presenter.UserPresenter;
import com.caij.codehub.ui.listener.UserFollowUi;
import com.caij.codehub.ui.listener.UserUi;
import com.caij.codehub.utils.TimeUtils;
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

    @Bind(R.id.img_user_avatar)
    ImageView mUserAvatarImageView;
    @Bind(R.id.tv_user_followers)
    TextView mUserFollowersTextView;
    @Bind(R.id.tv_user_repository)
    TextView mUserRepositoryTextView;
    @Bind(R.id.tv_user_following)
    TextView mUserFollowingTextView;
    @Bind(R.id.tv_user_location)
    TextView mUserLocationTextView;
    @Bind(R.id.tv_user_email)
    TextView mUserRepositoryEmailTextView;
    @Bind(R.id.tv_user_blog)
    TextView mUserBlogTextView;
    @Bind(R.id.tv_user_company)
    TextView mUserCompanyTextView;
    @Bind(R.id.tv_user_join_time)
    TextView mUserJoinTimeTextView;
    @Bind(R.id.tv_user_name)
    TextView mUserNameTextView;
    @Bind(R.id.tv_user_nickname)
    TextView mUserNicknameTextView;

    private User mUser;
    private String mToken;
    private String mUsername;
    private UserPresenter mUserPresenter;
    private Menu mMenu;
    private FollowActionPresent mFollowActionPresent;

    public static Intent newIntent(Activity activity, String name) {
        CheckValueUtil.check(name);
        Intent intent = new Intent(activity, UserInfoActivity.class);
        intent.putExtra(Constant.USER_NAME, name);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentContainer.setVisibility(View.GONE);
        mToken = SPUtils.get(Constant.USER_TOKEN, "");
        mUsername = getIntent().getStringExtra(Constant.USER_NAME);
        getSupportActionBar().setTitle(mUsername);
        mUserPresenter = PresenterFactory.newPresentInstance(UserPresenter.class, UserUi.class, this);
        mUserPresenter.getUserInfo(mToken, mUsername);

        if (!mUsername.equals(CodeHubApplication.getCurrentUserName())) { //如果是自己就不用加载follow信息
            mFollowActionPresent = PresenterFactory.newPresentInstance(FollowActionPresent.class,
                    UserFollowUi.class, this);
            mFollowActionPresent.checkFollowState(mToken, mUsername);
        }
    }

    protected void handlerData(User user) {
        mUser = user;
        showContentContainer();
        Glide.with(this).load(user.getAvatar_url()).placeholder(R.drawable.default_circle_head_image).
                bitmapTransform(new CropCircleTransformation(this)).into(mUserAvatarImageView);
        mUserFollowersTextView.setText(String.valueOf(user.getFollowers()));
        mUserFollowingTextView.setText(String.valueOf(user.getFollowing()));
        mUserLocationTextView.setText(user.getLocation());
        mUserCompanyTextView.setText(user.getCompany());
        mUserRepositoryEmailTextView.setText(user.getEmail());
        mUserBlogTextView.setText(user.getBlog());
        mUserJoinTimeTextView.setText(TimeUtils.getStringTime(user.getCreated_at()));
        mUserNameTextView.setText(user.getLogin());
        mUserNicknameTextView.setText(user.getName());
        mUserRepositoryTextView.setText(String.valueOf(user.getPublic_repos()));
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
    public void onGetUserInfoError(VolleyError error) {
        showError();
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
    public void onFollowActionLoading(int actionType) {
        showLoading();
    }

    @Override
    public void onFollowActionLoaded(int actionType) {
        hideLoading();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.follow) {
            if (item.getTitle().equals(getString(R.string.unfollow))) {
                mFollowActionPresent.unfollowUser(mToken, mUsername);
            }else {
                mFollowActionPresent.followUser(mToken, mUsername);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mUserPresenter.getUserInfo(mToken, mUsername);
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
            Intent intent = WebActivity.newIntent(this, blogUrl);
            startActivity(intent);
        }
    }

    @Override
    public void onLoading() {
        showLoading();
    }

    @Override
    public void onLoaded() {
        hideLoading();
    }
}
