package com.caij.codehub.present;

import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.interactor.RepositoryListInteractor;
import com.caij.codehub.present.ui.ListUi;

import java.util.List;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/16.
 */
public class RepositoriesPresent extends ListPresent<ListUi<Repository>, Repository>{

    private RepositoryListInteractor mRepositoryListInteractor;

    public RepositoriesPresent(ListUi<Repository> ui) {
        super(ui);
        mRepositoryListInteractor = InteractorFactory.newInteractorInstance(RepositoryListInteractor.class);
    }

    public void getUserStarredRepositories(final LoadType loadType, String username, String token, Page page) {
        mRepositoryListInteractor.getUserStarredRepositories(username, token, page, this, new DefaultInteractorCallback<List<Repository>>(mUi) {
            @Override
            public void onError(int msgId) {
                defaultDealWithError(msgId, loadType);
            }

            @Override
            public void onSuccess(List<Repository> repositories) {
                defaultDealWithSuccess(repositories, loadType);
            }

            @Override
            public void onLoading() {
                defaultDealWithLoading(loadType);
            }
        });
    }


    public void getUserRepositories(final LoadType loadType, String username, String token, Page page){
        mRepositoryListInteractor.getUserRepositories(username, token, page, this, new DefaultInteractorCallback<List<Repository>>(mUi) {
            @Override
            public void onError(int msgId) {
                defaultDealWithError(msgId, loadType);
            }

            @Override
            public void onSuccess(List<Repository> repositories) {
                defaultDealWithSuccess(repositories, loadType);
            }

            @Override
            public void onLoading() {
                defaultDealWithLoading(loadType);
            }
        });
    }

    public void getSearchRepository(final LoadType loadType, String q, String sort, String order, Page page){
        mRepositoryListInteractor.getSearchRepository(q, sort, order, page, this, new DefaultInteractorCallback<List<Repository>>(mUi) {
            @Override
            public void onError(int msgId) {
                defaultDealWithError(msgId, loadType);
            }

            @Override
            public void onSuccess(List<Repository> repositories) {
                defaultDealWithSuccess(repositories, loadType);
            }

            @Override
            public void onLoading() {
                defaultDealWithLoading(loadType);
            }
        });
    }

    public void getTrendingRepository(final LoadType loadType, String since, String language) {
        mRepositoryListInteractor.getTrendingRepository(since, language, this, new DefaultInteractorCallback<List<Repository>>(mUi) {
            @Override
            public void onError(int msgId) {
                defaultDealWithError(msgId, loadType);
            }

            @Override
            public void onSuccess(List<Repository> repositories) {
                defaultDealWithSuccess(repositories, loadType);
            }

            @Override
            public void onLoading() {
                defaultDealWithLoading(loadType);
            }
        });
    }

}
