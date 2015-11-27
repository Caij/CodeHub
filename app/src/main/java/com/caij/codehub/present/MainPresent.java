package com.caij.codehub.present;

import com.caij.codehub.bean.User;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.interactor.UserInteractor;
import com.caij.codehub.present.ui.MainUi;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/16.
 */
public class MainPresent extends Present<MainUi>{

    protected final UserInteractor mUserInterctor;

    public MainPresent(MainUi ui) {
        super(ui);
        mUserInterctor = InteractorFactory.newInteractorInstance(UserInteractor.class);
    }

    public void getUserInfo(String token, String username) {
        mUserInterctor.getUserInfo(token, username, this, new DefaultInteractorCallback<User>(mUi) {
            @Override
            public void onSuccess(User user) {
                MainUi ui = mUi.get();
                if (ui != null) {
                    ui.onGetUserInfoSuccess(user);
                }
            }

            @Override
            public void onLoading() {

            }

            @Override
            public void onError(int msgId) {
                MainUi ui = mUi.get();
                if (ui != null) {
                    ui.showError(msgId);
                }
            }
        });
    }

}
