package com.caij.codehub.presenter;

import com.caij.codehub.bean.Page;
import com.caij.codehub.ui.listener.RepositoryListUi;

/**
 * Created by Caij on 2015/9/18.
 */
public interface RepositoryListPresenter extends Present<RepositoryListUi> {

    public void getUserStarredRepositories(int loadType, String username, String token, Page page);

    public void getUserRepositories(int loadType, String username, String token, Page page);

    public void getSearchRepository(int loadType, String q, String sort, String order, Page page);

    public void getTrendingRepository(int loadType, String since, String language);

}
