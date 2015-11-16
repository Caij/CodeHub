package com.caij.codehub.present;

import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.User;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.interactor.UserListInteractor;
import com.caij.codehub.present.ui.ListUi;

import java.util.List;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/16.
 */
public class UsersPresent extends ListPresent<ListUi<User>, User>{

    private UserListInteractor mUserListInteractor;

    public UsersPresent(ListUi<User> ui) {
        super(ui);
        mUserListInteractor = InteractorFactory.newPresentInstance(UserListInteractor.class);
    }

    public void getFollowers(final LoadType loadType, String token, String username, Page page) {
        mUserListInteractor.getFollowers(token, username, page, this, new DefaultInteractorCallback<List<User>>(mUi) {
            @Override
            public void onError(int msgId) {
                defaultDealWithError(msgId, loadType);
            }

            @Override
            public void onSuccess(List<User> users) {
                defaultDealWithSuccess(users, loadType);
            }

            @Override
            public void onLoading() {
                defaultDealWithLoading(loadType);
            }
        });
    }

    public void getFollowing(final LoadType loadType, String token, String username, Page page){
        mUserListInteractor.getFollowing(token, username, page, this, new DefaultInteractorCallback<List<User>>(mUi) {
            @Override
            public void onError(int msgId) {
                defaultDealWithError(msgId, loadType);
            }

            @Override
            public void onSuccess(List<User> users) {
                defaultDealWithSuccess(users, loadType);
            }

            @Override
            public void onLoading() {
                defaultDealWithLoading(loadType);
            }
        });
    }
}
