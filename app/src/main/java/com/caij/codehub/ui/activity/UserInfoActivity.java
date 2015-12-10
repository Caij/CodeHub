package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.User;
import com.caij.codehub.present.UserInfoPresent;
import com.caij.codehub.present.ui.UserInfoUi;
import com.caij.codehub.present.ui.UserUi;
import com.caij.codehub.utils.AvatarUrlUtil;
import com.caij.codehub.utils.CropCircleTransformation;
import com.caij.codehub.utils.TimeUtils;
import com.caij.codehub.widgets.FloatingActionButton;
import com.caij.lib.utils.CheckValueUtil;
import com.caij.lib.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Caij on 2015/9/19.
 */
public class UserInfoActivity extends BaseCodeHubActivity implements UserUi, UserInfoUi {

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
    @Bind(R.id.toolbar)
    Toolbar mToolBar;
    @Bind(R.id.fab_star)
    FloatingActionButton mFabStart;
    @Bind(R.id.pb)
    ProgressBar pbLoading;
    @Bind(R.id.backdrop)
    ImageView mBackdrop;

    private User mUser;
    private String mToken;
    private String mUsername;
    private Boolean mStarStatus;
    private UserInfoPresent mUserInfoPresent;

    public static Intent newIntent(Activity activity, String name, String avatarUrl) {
        CheckValueUtil.check(name);
        Intent intent = new Intent(activity, UserInfoActivity.class);
        intent.putExtra(Constant.USER_NAME, name);
        intent.putExtra(Constant.AVATAR_URL, avatarUrl);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        handleIntent(getIntent());
    }

    protected void handleIntent(Intent intent) {
        mToken = CodeHubPrefs.get().getToken();
        mUsername = intent.getStringExtra(Constant.USER_NAME);
        String avatarUrl = intent.getStringExtra(Constant.AVATAR_URL);

        mUserNameTextView.setText(mUsername);
        Glide.with(this).load(AvatarUrlUtil.restoreAvatarUrl(avatarUrl)).
                placeholder(R.drawable.default_circle_head_image).diskCacheStrategy(DiskCacheStrategy.ALL).
                bitmapTransform(new CropCircleTransformation(this)).into(mUserAvatarImageView);
        Glide.with(this).load(R.drawable.user_info_bg).into(mBackdrop);

        mUserInfoPresent = new UserInfoPresent(this);
        mUserInfoPresent.getUserInfo(mToken, mUsername);

        if (!mUsername.equals(CodeHubPrefs.get().getUsername())) { //self
            mUserInfoPresent.getFollowState(mToken, mUsername);
        }else {
            onGetFollowStateSuccess(true);
        }
    }

    @Override
    protected int getAttachLayoutId() {
        return R.layout.activity_user_info;
    }

    protected void handlerData(User user) {
        mUser = user;
        if (user.getLogin().equals(CodeHubPrefs.get().getUsername())) {
            CodeHubPrefs.get().setUser(user);
        }
        mUserFollowersTextView.setText(String.valueOf(user.getFollowers()));
        mUserFollowingTextView.setText(String.valueOf(user.getFollowing()));
        mUserLocationTextView.setText(user.getLocation());
        mUserCompanyTextView.setText(user.getCompany());
        mUserRepositoryEmailTextView.setText(user.getEmail());
        mUserBlogTextView.setText(user.getBlog());
        mUserJoinTimeTextView.setText(TimeUtils.getStringTime(user.getCreated_at()));
        mUserRepositoryTextView.setText(String.valueOf(user.getPublic_repos()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showContentAnimLoading(boolean isVisible) {
        if (isVisible) {
            pbLoading.setVisibility(View.VISIBLE);
        } else {
            pbLoading.setVisibility(View.GONE);
        }
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mUserInfoPresent.getUserInfo(mToken, mUsername);
    }

    @OnClick(R.id.ll_user_followers)
    public void onFollowersClick() {
        if (mUser != null) {
            Intent intent = FollowersActivity.newIntent(this, mUser.getLogin());
            startActivity(intent);
        }
    }

    @OnClick(R.id.ll_user_following)
    public void onFollowingClick() {
        if (mUser != null) {
            Intent intent = FollowingActivity.newIntent(this, mUser.getLogin());
            startActivity(intent);
        }
    }

    @OnClick(R.id.ll_user_repository)
    public void onRepositoryClick() {
        if (mUser != null) {
            Intent intent = UserRepositoriesActivity.newIntent(this, mUser.getLogin());
            startActivity(intent);
        }
    }

    @OnClick(R.id.ll_blog)
    public void onBlogClick() {
        if (mUser != null) {
            String blogUrl = mUser.getBlog();
            if (!TextUtils.isEmpty(blogUrl)) {
                Intent intent = WebActivity.newIntent(this, "Blog", blogUrl);
                startActivity(intent);
            }
        }
    }

    @OnClick(R.id.fab_star)
    public void onFabClick(FloatingActionButton view) {
        if (mStarStatus == null) {
            mUserInfoPresent.getFollowState(mToken, mUsername);
            ToastUtil.show(this, getString(R.string.follow_info_error));
            return;
        }

        if (mStarStatus) {
            mUserInfoPresent.unfollowUser(mToken, mUsername);
        }else {
            mUserInfoPresent.followUser(mToken, mUsername);
        }
    }

    @OnClick(R.id.img_user_avatar)
    public void onUserAvatarClick(View view) {
        if (mUser != null) {
            Intent intent = PictureReviewActivity.newIntent(this, AvatarUrlUtil.restoreAvatarUrl(mUser.getAvatar_url()));
            ActivityOptionsCompat optionsCompat
                    = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this, view, Constant.TRANSIT_PIC);
            ActivityCompat.startActivity(this, intent,
                    optionsCompat.toBundle());
        }
    }

    @Override
    public void onGetUserInfoSuccess(User user) {
        handlerData(user);
    }

    @Override
    protected void onDestroy() {
        mUserInfoPresent.onDeath();
        super.onDestroy();
    }

    @Override
    public void onGetFollowStateSuccess(boolean isFollow) {
        mStarStatus = isFollow;
        if (isFollow) {
            mFabStart.setImageResource(R.drawable.heart_liked);
        } else {
            mFabStart.setImageResource(R.drawable.heart_like);
        }
    }

    @Override
    public void onFollowSuccess() {
        ToastUtil.show(UserInfoActivity.this, R.string.follow_success);
        mFabStart.setImageResource(R.drawable.heart_liked);
    }

    @Override
    public void onUnfollowSuccess() {
        ToastUtil.show(UserInfoActivity.this, R.string.unfollow_success);
        mFabStart.setImageResource(R.drawable.heart_like);
    }

    @Override
    protected void showError() {
    }
}
