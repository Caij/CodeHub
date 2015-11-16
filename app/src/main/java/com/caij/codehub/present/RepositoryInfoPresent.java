package com.caij.codehub.present;

import com.android.volley.NetworkResponse;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.interactor.RepositoryActionInteractor;
import com.caij.codehub.interactor.RepositoryInfoInteractor;
import com.caij.codehub.present.ui.RepositoryInfoUi;
import com.caij.lib.utils.VolleyManager;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/16.
 */
public class RepositoryInfoPresent extends Present<RepositoryInfoUi>{

    public final Object requestTag;
    public final RepositoryInfoInteractor mRepositoryInfoInteractor;
    public final RepositoryActionInteractor mRepositoryActionInteractor;

    public RepositoryInfoPresent(RepositoryInfoUi ui) {
        super(ui);
        requestTag = new Object();
        mRepositoryInfoInteractor = InteractorFactory.newPresentInstance(RepositoryInfoInteractor.class);
        mRepositoryActionInteractor = InteractorFactory.newPresentInstance(RepositoryActionInteractor.class);
    }

    public void getRepositoryInfo(String repositoryName, String owner, String token) {
        mRepositoryInfoInteractor.getRepositoryInfo(repositoryName, owner, token, requestTag, new DefaultInteractorCallback<Repository>(mUi) {
            @Override
            public void onError(int msgId) {
                mUi.hideLoading();
                mUi.showError(msgId);
                mUi.showErrorView();
            }

            @Override
            public void onSuccess(Repository repository) {
                mUi.hideLoading();
                mUi.getRepositoryInfoSuccess(repository);
            }

            @Override
            public void onLoading() {
                mUi.showLoading();
            }
        });
    }

    public void hasStarRepo(String owner, String repo, String token){
        mRepositoryActionInteractor.hasStarRepo(owner, repo, token, requestTag, new DefaultInteractorCallback<Boolean>(mUi) {
            @Override
            public void onError(int msgId) {

            }

            @Override
            public void onSuccess(Boolean aBoolean) {
                mUi.getStarStateSuccess(aBoolean);
            }

            @Override
            public void onLoading() {

            }
        });
    }

    public void starRepo(String owner, String repo, String token){
        mRepositoryActionInteractor.starRepo(owner, repo, token, requestTag, new DefaultInteractorCallback<NetworkResponse>(mUi) {
            @Override
            public void onSuccess(NetworkResponse response) {
                mUi.hideLoading();
                mUi.starSuccess();
            }

            @Override
            public void onLoading() {
                mUi.showLoading();
            }

            @Override
            public void onError(int msgId) {
                mUi.hideLoading();
                mUi.showError(msgId);
            }
        });
    }

    public void unstarRepo(String owner, String repo, String token){
        mRepositoryActionInteractor.unstarRepo(owner, repo, token, requestTag, new DefaultInteractorCallback<NetworkResponse>(mUi) {
            @Override
            public void onSuccess(NetworkResponse response) {
                mUi.hideLoading();
                mUi.unstarSuccess();
            }

            @Override
            public void onLoading() {
                mUi.showLoading();
            }

            @Override
            public void onError(int msgId) {
                mUi.hideLoading();
                mUi.showError(msgId);
            }
        });
    }

    public void forkRepo(String owner, String repo, String token){
        mRepositoryActionInteractor.forkRepo(owner, repo, token, requestTag, new DefaultInteractorCallback<NetworkResponse>(mUi) {
            @Override
            public void onSuccess(NetworkResponse response) {
                mUi.hideLoading();
                mUi.forkSuccess();
            }

            @Override
            public void onLoading() {
                mUi.showLoading();
            }

            @Override
            public void onError(int msgId) {
                mUi.hideLoading();
                mUi.showError(msgId);
            }
        });
    }

    @Override
    public void onDeath() {
        VolleyManager.cancelRequestByTag(requestTag);
    }
}
