package com.caij.codehub.presenter.imp;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.bean.SearchRepository;
import com.caij.codehub.presenter.RepositoryListPresenter;
import com.caij.codehub.ui.listener.RepositoryListUi;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Caij on 2015/9/18.
 */
public class RepositoryListPresenterImp implements RepositoryListPresenter {

    protected RepositoryListUi mUi;
    private Object tag = new Object();

    public RepositoryListPresenterImp(RepositoryListUi ui) {
        this.mUi = ui;
    }

    @Override
    public void getUserStarredRepositories(int loadType, String username, String token, Page page) {
        String url = API.API_HOST + "/users/" + username + API.REPOSITORY_STARRED_URI;
        loadStarredOrUserRepository(loadType, url, token, page);
    }

    @Override
    public void getUserRepositories(int loadType, String username, String token, Page page) {
        String url = API.API_HOST + "/users/" + username + API.REPOSITORY_REPOS_URI;
        loadStarredOrUserRepository(loadType, url, token, page);
    }

    @Override
    public void getSearchRepository(final int loadType, String q, String sort, String order, Page page) {
        mUi.onLoading(loadType);
        Map<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(q)) {
            params.put(API.Q, q);
        }
        if (!TextUtils.isEmpty(order)) {
            params.put(API.ORDER, order);
        }
        params.put(API.SORT, sort);
        params.put(API.PAGE, String.valueOf(page.getPageIndex()));
        params.put(API.PER_PAGE, String.valueOf(page.getPageDataCount()));
        String url = API.API_HOST + API.SEARCH_REPOSITORY_URI;
        GsonRequest<SearchRepository> request = new GsonRequest<SearchRepository>(Request.Method.GET, url, params,
                new TypeToken<SearchRepository>() {}.getType(), new Response.Listener<SearchRepository>() {
            @Override
            public void onResponse(SearchRepository response) {
                if (response != null) {
                    handlerResponse(loadType, response.getItems());
                }else {
                    handlerError(loadType, null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handlerError(loadType, error);
            }
        });
        VolleyUtil.addRequest(request, tag);
    }

    @Override
    public void getTrendingRepository(final int loadType, String since, String language) {
        mUi.onLoading(loadType);
        Map<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(language))
            params.put(API.TENDING_REPOSITORY_PARAM_LANGUAGE, language);
        if (!TextUtils.isEmpty(since))
            params.put(API.TENDING_REPOSITORY_PARAM_SINCE, since);
        String url = API.TRENDING_REPOSITORY_HOST;
        GsonRequest<List<Repository>> request = new GsonRequest<List<Repository>>(Request.Method.GET, url, params, null,
                new TypeToken<List<Repository>>() {}.getType(), new Response.Listener<List<Repository>>() {
            @Override
            public void onResponse(List<Repository> response) {
                if (response != null) {
                    handlerResponse(loadType, response);
                }else {
                    handlerError(loadType, null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handlerError(loadType, error);
            }
        });
        VolleyUtil.addRequest(request, tag);
    }

    private void loadStarredOrUserRepository(final int loadType, String url, String token, Page page) {
        mUi.onLoading(loadType);
        Map<String, String> params = new HashMap<>();
        params.put(API.PAGE, String.valueOf(page.getPageIndex()));
        params.put(API.PER_PAGE, String.valueOf(page.getPageDataCount()));
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        GsonRequest<List<Repository>> request = new GsonRequest<List<Repository>>(Request.Method.GET, url, params, head,
                new TypeToken<List<Repository>>() {}.getType(), new Response.Listener<List<Repository>>() {
            @Override
            public void onResponse(List<Repository> response) {
                if (response != null) {
                    handlerResponse(loadType, response);
                }else {
                    handlerError(loadType, null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               handlerError(loadType, error);
            }
        });
        VolleyUtil.addRequest(request, tag);
    }

    private void handlerError(int loadType, VolleyError error) {
        mUi.onLoaded(loadType);
        if (loadType == LoadType.FIRSTLOAD) {
            mUi.onFirstLoadError(error);
        }else if (loadType == LoadType.REFRESH) {
            mUi.onRefreshError(error);
        }else if (loadType == LoadType.LOADMOER) {
            mUi.onLoadMoreError(error);
        }
    }

    private void handlerResponse(int loadType, List<Repository> repositories) {
        mUi.onLoaded(loadType);
        if (loadType == LoadType.FIRSTLOAD) {
            mUi.onFirstLoadSuccess(repositories);
        }else if (loadType == LoadType.REFRESH){
           mUi.onRefreshSuccess(repositories);
        }  else if (loadType == LoadType.LOADMOER) {
            mUi.onLoadMoreSuccess(repositories);
        }
    }

    @Override
    public void onDeath() {
        VolleyUtil.cancelRequestByTag(tag);
    }
}
