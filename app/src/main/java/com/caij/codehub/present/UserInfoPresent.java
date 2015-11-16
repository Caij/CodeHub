package com.caij.codehub.present;

import com.android.volley.NetworkResponse;
import com.caij.codehub.bean.User;
import com.caij.codehub.interactor.FollowActionInteractor;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.interactor.UserInteractor;
import com.caij.codehub.present.ui.UserInfoUi;
import com.caij.lib.utils.VolleyManager;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/16.
 */
public class UserInfoPresent extends Present<UserInfoUi>{

    private final Object requestTag;
    private final FollowActionInteractor mFollowActionInteractor;
    private UserInteractor mUserInterctor;

    public UserInfoPresent(UserInfoUi ui) {
        super(ui);
        requestTag = new Object();
        mUserInterctor = InteractorFactory.newPresentInstance(UserInteractor.class);
        mFollowActionInteractor = InteractorFactory.newPresentInstance(FollowActionInteractor.class);
    }

    public void getUserInfo(String token, String username) {
        mUserInterctor.getUserInfo(token, username, requestTag, new DefaultInteractorCallback<User>(mUi) {
            @Override
            public void onError(int msgId) {
                mUi.hideLoading();
                mUi.showErrorView();
                mUi.showError(msgId);
            }

            @Override
            public void onSuccess(User user) {
                mUi.hideLoading();
                mUi.onGetUserInfoSuccess(user);
            }

            @Override
            public void onLoading() {
                mUi.showLoading();
            }
        });
    }

    public void getFollowState(String token, String username) {
        mFollowActionInteractor.getFollowState(token, username, requestTag, new DefaultInteractorCallback<Boolean>(mUi) {
            @Override
            public void onError(int msgId) {

            }

            @Override
            public void onSuccess(Boolean aBoolean) {
                mUi.onGetFollowStateSuccess(aBoolean);
            }

            @Override
            public void onLoading() {

            }
        });
    }

    public void followUser(String token, String username) {
        mFollowActionInteractor.followUser(token, username, requestTag, new DefaultInteractorCallback<NetworkResponse>(mUi) {
            @Override
            public void onError(int msgId) {
                mUi.hideLoading();
                mUi.showError(msgId);
            }

            @Override
            public void onSuccess(NetworkResponse response) {
                mUi.hideLoading();
                mUi.onFollowSuccess();
            }

            @Override
            public void onLoading() {
                mUi.showLoading();
            }
        });
    }

    public void unfollowUser(String token, String username) {
        mFollowActionInteractor.unfollowUser(token, username, requestTag, new DefaultInteractorCallback<NetworkResponse>(mUi) {
            @Override
            public void onError(int msgId) {
                mUi.hideLoading();
                mUi.showError(msgId);
            }

            @Override
            public void onSuccess(NetworkResponse response) {
                mUi.hideLoading();
                mUi.onUnfollowSuccess();
            }

            @Override
            public void onLoading() {
                mUi.showLoading();
            }
        });
    }

    @Override
    public void onDeath() {
        VolleyManager.cancelRequestByTag(requestTag);
    }
}
