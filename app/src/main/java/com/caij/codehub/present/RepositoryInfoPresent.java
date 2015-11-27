package com.caij.codehub.present;

import com.android.volley.NetworkResponse;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.interactor.RepositoryActionInteractor;
import com.caij.codehub.interactor.RepositoryInfoInteractor;
import com.caij.codehub.present.ui.RepositoryInfoUi;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/16.
 */
public class RepositoryInfoPresent extends Present<RepositoryInfoUi>{

    public final RepositoryInfoInteractor mRepositoryInfoInteractor;
    public final RepositoryActionInteractor mRepositoryActionInteractor;

    public RepositoryInfoPresent(RepositoryInfoUi ui) {
        super(ui);
        mRepositoryInfoInteractor = InteractorFactory.newInteractorInstance(RepositoryInfoInteractor.class);
        mRepositoryActionInteractor = InteractorFactory.newInteractorInstance(RepositoryActionInteractor.class);
    }

    public void getRepositoryInfo(String repositoryName, String owner, String token) {
        mRepositoryInfoInteractor.getRepositoryInfo(repositoryName, owner, token, this, new DefaultInteractorCallback<Repository>(mUi) {
            @Override
            public void onError(int msgId) {
                RepositoryInfoUi repositoryInfoUi = mUi.get();
                if (repositoryInfoUi != null) {
                    repositoryInfoUi.showContentAnimLoading(false);
                    repositoryInfoUi.showError(msgId);
                    repositoryInfoUi.showContentError();
                }
            }

            @Override
            public void onSuccess(Repository repository) {
                RepositoryInfoUi repositoryInfoUi = mUi.get();
                if (repositoryInfoUi != null) {
                    repositoryInfoUi.showContentAnimLoading(false);
                    repositoryInfoUi.getRepositoryInfoSuccess(repository);
                }
            }

            @Override
            public void onLoading() {
                RepositoryInfoUi repositoryInfoUi = mUi.get();
                if (repositoryInfoUi != null) {
                    repositoryInfoUi.showContentAnimLoading(true);
                }
            }
        });
    }

    public void hasStarRepo(String owner, String repo, String token){
        mRepositoryActionInteractor.hasStarRepo(owner, repo, token, this, new DefaultInteractorCallback<Boolean>(mUi) {
            @Override
            public void onError(int msgId) {

            }

            @Override
            public void onSuccess(Boolean aBoolean) {
                RepositoryInfoUi repositoryInfoUi = mUi.get();
                if (repositoryInfoUi != null) {
                    repositoryInfoUi.getStarStateSuccess(aBoolean);
                }
            }

            @Override
            public void onLoading() {

            }
        });
    }

    public void starRepo(String owner, String repo, String token){
        mRepositoryActionInteractor.starRepo(owner, repo, token, this, new DefaultInteractorCallback<NetworkResponse>(mUi) {
            @Override
            public void onSuccess(NetworkResponse response) {
                RepositoryInfoUi repositoryInfoUi = mUi.get();
                if (repositoryInfoUi != null) {
                    repositoryInfoUi.showProgressBarLoading(false);
                    repositoryInfoUi.starSuccess();
                }
            }

            @Override
            public void onLoading() {
                RepositoryInfoUi repositoryInfoUi = mUi.get();
                if (repositoryInfoUi != null) {
                    repositoryInfoUi.showProgressBarLoading(true);
                }
            }

            @Override
            public void onError(int msgId) {
                RepositoryInfoUi repositoryInfoUi = mUi.get();
                if (repositoryInfoUi != null) {
                    repositoryInfoUi.showProgressBarLoading(false);
                    repositoryInfoUi.showError(msgId);
                }
            }
        });
    }

    public void unstarRepo(String owner, String repo, String token){
        mRepositoryActionInteractor.unstarRepo(owner, repo, token, this, new DefaultInteractorCallback<NetworkResponse>(mUi) {
            @Override
            public void onSuccess(NetworkResponse response) {
                RepositoryInfoUi repositoryInfoUi = mUi.get();
                if (repositoryInfoUi != null) {
                    repositoryInfoUi.showProgressBarLoading(false);
                    repositoryInfoUi.unstarSuccess();
                }
            }

            @Override
            public void onLoading() {
                RepositoryInfoUi repositoryInfoUi = mUi.get();
                if (repositoryInfoUi != null) {
                    repositoryInfoUi.showProgressBarLoading(true);
                }
            }

            @Override
            public void onError(int msgId) {
                RepositoryInfoUi repositoryInfoUi = mUi.get();
                if (repositoryInfoUi != null) {
                    repositoryInfoUi.showProgressBarLoading(false);
                    repositoryInfoUi.showError(msgId);
                }
            }
        });
    }

    public void forkRepo(String owner, String repo, String token){
        mRepositoryActionInteractor.forkRepo(owner, repo, token, this, new DefaultInteractorCallback<NetworkResponse>(mUi) {
            @Override
            public void onSuccess(NetworkResponse response) {
                RepositoryInfoUi repositoryInfoUi = mUi.get();
                if (repositoryInfoUi != null) {
                    repositoryInfoUi.showProgressBarLoading(false);
                    repositoryInfoUi.forkSuccess();
                }
            }

            @Override
            public void onLoading() {
                RepositoryInfoUi repositoryInfoUi = mUi.get();
                if (repositoryInfoUi != null) {
                    repositoryInfoUi.showProgressBarLoading(true);
                }
            }

            @Override
            public void onError(int msgId) {
                RepositoryInfoUi repositoryInfoUi = mUi.get();
                if (repositoryInfoUi != null) {
                    repositoryInfoUi.showProgressBarLoading(false);
                    repositoryInfoUi.showError(msgId);
                }
            }
        });
    }

}
