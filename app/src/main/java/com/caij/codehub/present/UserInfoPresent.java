package com.caij.codehub.present;

import com.android.volley.NetworkResponse;
import com.caij.codehub.bean.User;
import com.caij.codehub.interactor.FollowActionInteractor;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.interactor.UserInteractor;
import com.caij.codehub.present.ui.UserInfoUi;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/16.
 */
public class UserInfoPresent extends Present<UserInfoUi>{

    private final FollowActionInteractor mFollowActionInteractor;
    private final UserInteractor mUserInterctor;

    public UserInfoPresent(UserInfoUi ui) {
        super(ui);
        mUserInterctor = InteractorFactory.newInteractorInstance(UserInteractor.class);
        mFollowActionInteractor = InteractorFactory.newInteractorInstance(FollowActionInteractor.class);
    }

    public void getUserInfo(String token, String username) {
        mUserInterctor.getUserInfo(token, username, this, new DefaultInteractorCallback<User>(mUi) {
            @Override
            public void onError(int msgId) {
                UserInfoUi userInfoUi = mUi.get();
                if (userInfoUi != null) {
                    userInfoUi.showContentAnimLoading(false);
                    userInfoUi.showContentError();
                    userInfoUi.showError(msgId);
                }
            }

            @Override
            public void onSuccess(User user) {
                UserInfoUi userInfoUi = mUi.get();
                if (userInfoUi != null) {
                    userInfoUi.showContentAnimLoading(false);
                    userInfoUi.onGetUserInfoSuccess(user);
                }
            }

            @Override
            public void onLoading() {
                UserInfoUi userInfoUi = mUi.get();
                if (userInfoUi != null) {
                    userInfoUi.showContentAnimLoading(true);
                }
            }
        });
    }

    public void getFollowState(String token, String username) {
        mFollowActionInteractor.getFollowState(token, username, this, new DefaultInteractorCallback<Boolean>(mUi) {
            @Override
            public void onError(int msgId) {

            }

            @Override
            public void onSuccess(Boolean aBoolean) {
                UserInfoUi userInfoUi = mUi.get();
                if (userInfoUi != null) {
                    userInfoUi.onGetFollowStateSuccess(aBoolean);
                }
            }

            @Override
            public void onLoading() {

            }
        });
    }

    public void followUser(String token, String username) {
        mFollowActionInteractor.followUser(token, username, this, new DefaultInteractorCallback<NetworkResponse>(mUi) {
            @Override
            public void onError(int msgId) {
                UserInfoUi userInfoUi = mUi.get();
                if (userInfoUi != null) {
                    userInfoUi.showProgressBarLoading(false);
                    userInfoUi.showError(msgId);
                }
            }

            @Override
            public void onSuccess(NetworkResponse response) {
                UserInfoUi userInfoUi = mUi.get();
                if (userInfoUi != null) {
                    userInfoUi.showProgressBarLoading(false);
                    userInfoUi.onFollowSuccess();
                }
            }

            @Override
            public void onLoading() {
                UserInfoUi userInfoUi = mUi.get();
                if (userInfoUi != null) {
                    userInfoUi.showProgressBarLoading(true);
                }
            }
        });
    }

    public void unfollowUser(String token, String username) {
        mFollowActionInteractor.unfollowUser(token, username, this, new DefaultInteractorCallback<NetworkResponse>(mUi) {
            @Override
            public void onError(int msgId) {
                UserInfoUi userInfoUi = mUi.get();
                if (userInfoUi != null) {
                    userInfoUi.showProgressBarLoading(false);
                    userInfoUi.showError(msgId);
                }
            }

            @Override
            public void onSuccess(NetworkResponse response) {
                UserInfoUi userInfoUi = mUi.get();
                if (userInfoUi != null) {
                    userInfoUi.showProgressBarLoading(true);
                    userInfoUi.onUnfollowSuccess();
                }
            }

            @Override
            public void onLoading() {
                UserInfoUi userInfoUi = mUi.get();
                if (userInfoUi != null) {
                    userInfoUi.showProgressBarLoading(false);
                }
            }
        });
    }

}
