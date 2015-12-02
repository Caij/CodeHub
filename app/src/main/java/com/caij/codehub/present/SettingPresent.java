package com.caij.codehub.present;

import com.android.volley.NetworkResponse;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.interactor.AuthenticationInteractor;
import com.caij.codehub.present.ui.SettingUi;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/17.
 */
public class SettingPresent extends Present<SettingUi>{

    private final AuthenticationInteractor mAuthenticationInteractor;

    public SettingPresent(SettingUi ui) {
        super(ui);
        mAuthenticationInteractor = InteractorFactory.newInteractorInstance(AuthenticationInteractor.class);
    }

    public void logout(String base64UsernameAndPwd, String tokenId){
        mAuthenticationInteractor.logout(base64UsernameAndPwd, tokenId, this, new DefaultInteractorCallback<NetworkResponse>(mUi) {
            @Override
            public void onError(int msgId) {
                mUi.showLogoutLoading(false);
                mUi.showError(msgId);
            }

            @Override
            public void onSuccess(NetworkResponse response) {
                mUi.showLogoutLoading(false);
                mUi.logoutSuccess();
            }

            @Override
            public void onLoading() {
                mUi.showLogoutLoading(true);
            }
        });
    }
}
