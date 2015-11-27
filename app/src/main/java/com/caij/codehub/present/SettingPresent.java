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
                SettingUi settingUi = mUi.get();
                if (settingUi != null) {
                    settingUi.showProgressBarLoading(false);
                    settingUi.showError(msgId);
                }
            }

            @Override
            public void onSuccess(NetworkResponse response) {
                SettingUi settingUi = mUi.get();
                if (settingUi != null) {
                    settingUi.showProgressBarLoading(false);
                    settingUi.logoutSuccess();
                }
            }

            @Override
            public void onLoading() {
                SettingUi settingUi = mUi.get();
                if (settingUi != null) {
                    settingUi.showProgressBarLoading(true);
                }
            }
        });
    }
}
