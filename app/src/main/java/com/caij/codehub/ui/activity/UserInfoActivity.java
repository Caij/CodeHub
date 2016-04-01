package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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
import com.caij.util.CheckValueUtil;
import com.caij.util.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Caij on 2015/9/19.
 */
public class UserInfoActivity extends BaseCodeHubToolBarActivity implements UserUi, UserInfoUi {

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
    private Menu mMenu;
    private UserInfoPresent mUserInfoPresent;

    public static Intent newIntent(Activity activity, String name) {
        CheckValueUtil.check(name);
        Intent intent = new Intent(activity, UserInfoActivity.class);
        intent.putExtra(Constant.USER_NAME, name);
        return intent;
    }

    @Override
    protected void handleIntent(Intent intent) {
        hideContentContainer();
        mToken = CodeHubPrefs.get().getToken();
        mUsername = intent.getStringExtra(Constant.USER_NAME);
        setTitle(mUsername);

        mUserInfoPresent = new UserInfoPresent(this);
        mUserInfoPresent.getUserInfo(mToken, mUsername);

        if (!mUsername.equals(CodeHubPrefs.get().getUsername())) { //self
            mUserInfoPresent.getFollowState(mToken, mUsername);
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
        showContentContainer();
        Glide.with(this).load(AvatarUrlUtil.restoreAvatarUrl(user.getAvatar_url())).
                placeholder(R.drawable.ic_default_circle_head_image).diskCacheStrategy(DiskCacheStrategy.ALL).
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
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.follow) {
            if (item.getTitle().equals(getString(R.string.unfollow))) {
                mUserInfoPresent.unfollowUser(mToken, mUsername);
            }else {
                mUserInfoPresent.followUser(mToken, mUsername);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mUserInfoPresent.getUserInfo(mToken, mUsername);
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
            Intent intent = WebActivity.newIntent(this, "Blog", blogUrl);
            startActivity(intent);
        }
    }

    @OnClick(R.id.img_user_avatar)
    public void onUserAvatarClick(View view) {
        Intent intent = PictureReviewActivity.newIntent(this, AvatarUrlUtil.restoreAvatarUrl(mUser.getAvatar_url()));
        ActivityOptionsCompat optionsCompat
                = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, view, Constant.TRANSIT_PIC);
        ActivityCompat.startActivity(this, intent,
                optionsCompat.toBundle());
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
        getMenuInflater().inflate(R.menu.menu_user, mMenu);
        mMenu.findItem(R.id.follow).setTitle(isFollow ? getString(R.string.unfollow) : getString(R.string.follow));
    }

    @Override
    public void onFollowSuccess() {
        ToastUtil.show(UserInfoActivity.this, R.string.follow_success);
        mMenu.findItem(R.id.follow).setTitle(R.string.unfollow);
    }

    @Override
    public void onUnfollowSuccess() {
        ToastUtil.show(UserInfoActivity.this, R.string.unfollow_success);
        mMenu.findItem(R.id.follow).setTitle(R.string.follow);
    }

}
