package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.R;
import com.caij.codehub.bean.User;
import com.caij.codehub.present.MainPresent;
import com.caij.codehub.present.ui.MainUi;
import com.caij.codehub.present.ui.UserUi;
import com.caij.codehub.ui.fragment.EventsFragment;
import com.caij.codehub.ui.fragment.RepositoryPagesFragment;
import com.caij.codehub.utils.AvatarUrlUtil;
import com.caij.codehub.utils.CropCircleTransformation;
import com.caij.lib.utils.LogUtil;
import com.caij.lib.utils.ToastUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MainActivity extends BaseCodeHubToolBarActivity implements MainUi {

    @Bind(R.id.img_navigation_avatar)
    ImageView mNavigationAvatarImageView;
    @Bind(R.id.tv_navigation_username)
    TextView mNavigationUsernameTextView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private User mUser;

    private Fragment mCurrentShowFragment;
    private RepositoryPagesFragment mRepositoryPagesFragment;
    private EventsFragment mEventsFragment;
    private MainPresent mMainPresent;

    public static Intent newIntent(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        return intent;
    }

    @Override
    protected void handleIntent(Intent intent) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        toggle.syncState();
        mDrawerLayout.setDrawerListener(toggle);
        mMainPresent = new MainPresent(this);

        mRepositoryPagesFragment = new RepositoryPagesFragment();
        mEventsFragment = new EventsFragment();
        mEventsFragment.setUserVisibleHint(true);

        getSupportFragmentManager().beginTransaction().add(R.id.main_content, mRepositoryPagesFragment).commit();
        mCurrentShowFragment = mRepositoryPagesFragment;

        User user = CodeHubPrefs.get().getUser();
        if (user == null) {
            mMainPresent.getUserInfo(CodeHubPrefs.get().getToken(), CodeHubPrefs.get().getUsername());
        }else {
            onGetUserInfoSuccess(user);
        }
    }

    @Override
    public void onGetUserInfoSuccess(User user) {
        mUser = user;
        CodeHubPrefs.get().setUser(user);
        mNavigationUsernameTextView.setText(user.getLogin());
        Glide.with(MainActivity.this).load(AvatarUrlUtil.restoreAvatarUrl(user.getAvatar_url())).
                placeholder(R.drawable.default_circle_head_image).diskCacheStrategy(DiskCacheStrategy.ALL).
                bitmapTransform(new CropCircleTransformation(MainActivity.this)).into(mNavigationAvatarImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                startActivity(new Intent(this, SearchActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mMainPresent.onDeath();
        super.onDestroy();
    }

    @Override
    protected int getAttachLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.img_navigation_avatar)
    public void onUserOnClick() {
        if (mUser == null)  {
            ToastUtil.show(this, R.string.user_info_error);
            mMainPresent.getUserInfo(CodeHubPrefs.get().getToken(),  CodeHubPrefs.get().getUsername());
            return;
        }
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        Intent intent = UserInfoActivity.newIntent(this, mUser.getLogin());
        startActivity(intent);
    }

    @OnCheckedChanged(R.id.rb_repository)
    public void onRepositoryChecked(RadioButton radioButton, boolean isCheck) {
        if (isCheck) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            switchContent(mCurrentShowFragment, mRepositoryPagesFragment, R.id.main_content);
            mCurrentShowFragment = mRepositoryPagesFragment;
        }
    }

    @OnCheckedChanged(R.id.rb_event)
    public void onEventChecked(RadioButton radioButton, boolean isCheck) {
        if (isCheck) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            switchContent(mCurrentShowFragment, mEventsFragment, R.id.main_content);
            mCurrentShowFragment = mEventsFragment;
        }
    }

    @OnClick(R.id.ll_setting)
    public void onSettingClick() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ll_about)
    public void onAboutClick() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void switchContent(Fragment from, Fragment to, int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            transaction.hide(from).add(id, to).commit();
        } else {
            transaction.hide(from).show(to).commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
