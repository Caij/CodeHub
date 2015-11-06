package com.caij.codehub.presenter;

import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.ui.callback.UiCallBack;

import java.util.List;

/**
 * Created by Caij on 2015/9/18.
 */
public interface RepositoryListPresenter extends Present {

    public void getUserStarredRepositories(String username, String token, Page page, Object requestTag, UiCallBack<List<Repository>> uiCallBack);

    public void getUserRepositories(String username, String token, Page page, Object requestTag, UiCallBack<List<Repository>> uiCallBack);

    public void getSearchRepository(String q, String sort, String order, Page page, Object requestTag, UiCallBack<List<Repository>> uiCallBack);

    public void getTrendingRepository(String since, String language, Object requestTag, UiCallBack<List<Repository>> uiCallBack);

}
