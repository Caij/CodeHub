package com.caij.codehub.present;

import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.interactor.RepositoryListInteractor;
import com.caij.codehub.present.ui.ListUi;
import com.caij.lib.utils.VolleyManager;

import java.util.List;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/16.
 */
public class RepositoriesPresent extends ListPresent<ListUi<Repository>, Repository>{

    private RepositoryListInteractor mRepositoryListInteractor;
    private Object mRequestTag;

    public RepositoriesPresent(ListUi<Repository> ui) {
        super(ui);
        mRepositoryListInteractor = InteractorFactory.newPresentInstance(RepositoryListInteractor.class);
        mRequestTag = new Object();
    }

    public void getUserStarredRepositories(final LoadType loadType, String username, String token, Page page) {
        mRepositoryListInteractor.getUserStarredRepositories(username, token, page, mRequestTag, new DefaultInteractorCallback<List<Repository>>(mUi) {
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
        mRepositoryListInteractor.getUserRepositories(username, token, page, mRequestTag, new DefaultInteractorCallback<List<Repository>>(mUi) {
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
        mRepositoryListInteractor.getSearchRepository(q, sort, order, page, mRequestTag, new DefaultInteractorCallback<List<Repository>>(mUi) {
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

    public void getTrendingRepository(final LoadType loadType, String since, String language, Page page) {
        mRepositoryListInteractor.getTrendingRepository(since, language, page, mRequestTag, new DefaultInteractorCallback<List<Repository>>(mUi) {
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

    @Override
    public void onDeath() {
        VolleyManager.cancelRequestByTag(mRequestTag);
    }
}
