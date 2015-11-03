package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.caij.codehub.CodeHubApplication;
import com.caij.codehub.R;
import com.caij.codehub.bean.User;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.presenter.UserPresenter;
import com.caij.codehub.ui.fragment.EventsFragment;
import com.caij.codehub.ui.fragment.RepositoryPagesFragment;
import com.caij.codehub.ui.listener.UserUi;
import com.caij.lib.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MainActivity extends BaseCodeHubActivity implements UserUi, NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.img_navigation_avatar)
    ImageView mNavigationAvatarImageView;
    @Bind(R.id.tv_navigation_username)
    TextView mNavigationUsernameTextView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

    private User mUser;

    private Fragment mCurrentShowFragment;
    private RepositoryPagesFragment mRepositoryPagesFragment;
    private EventsFragment mNewsFragment;
    private UserPresenter mUserPresenter;

    public static Intent newIntent(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        toggle.syncState();
        mDrawerLayout.setDrawerListener(toggle);
        mNavigationView.setNavigationItemSelectedListener(this);
        mUserPresenter = PresenterFactory.newPresentInstance(UserPresenter.class, UserUi.class, this);
        mUserPresenter.getUserInfo(CodeHubApplication.getToken(), CodeHubApplication.getCurrentUserName());

        mRepositoryPagesFragment = new RepositoryPagesFragment();
        mNewsFragment = new EventsFragment();
        mNewsFragment.setUserVisibleHint(true);

        getSupportFragmentManager().beginTransaction().add(R.id.main_content, mRepositoryPagesFragment).commit();
        mCurrentShowFragment = mRepositoryPagesFragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onGetUserInfoSuccess(User user) {
        mUser = user;
        mNavigationUsernameTextView.setText(user.getLogin());
        Glide.with(this).load(user.getAvatar_url()).placeholder(R.drawable.default_circle_head_image).
                bitmapTransform(new CropCircleTransformation(this)).into(mNavigationAvatarImageView);
    }

    @Override
    public void onGetUserInfoError(VolleyError error) {
        ToastUtil.show(this, "load user info error");
    }

    @OnClick(R.id.img_navigation_avatar)
    public void onUserOnClick() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        Intent intent = UserInfoActivity.newIntent(this, mUser.getLogin());
        startActivity(intent);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded() {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        switch (menuItem.getItemId()) {
            case R.id.nav_repository:
                if (menuItem.isChecked()) return true;
                menuItem.setChecked(true);
                switchContent(mCurrentShowFragment, mRepositoryPagesFragment, R.id.main_content);
                mCurrentShowFragment = mRepositoryPagesFragment;
                break;

            case R.id.nav_events:
                if (menuItem.isChecked()) return true;
                menuItem.setChecked(true);
                switchContent(mCurrentShowFragment, mNewsFragment, R.id.main_content);
                mCurrentShowFragment = mNewsFragment;
                break;

            case R.id.nav_setting:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
