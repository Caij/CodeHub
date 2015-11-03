package com.caij.codehub.presenter.imp;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.presenter.RepositoryActionPresent;
import com.caij.codehub.ui.listener.RepositoryActionUi;
import com.caij.lib.utils.LogUtil;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.volley.request.NetworkResponseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Caij on 2015/10/31.
 */
public class RepositoryActionPresentImp implements RepositoryActionPresent {

    private RepositoryActionUi mUi;
    private Object tag = new Object();

    public RepositoryActionPresentImp(RepositoryActionUi ui) {
        this.mUi = ui;
    }

    @Override
    public void hasStarRepo(String owner, String repo, String token) {
        mUi.onRepositoryActionLoading(ACTION_TYPE_HAS_STAR);
        String url = API.API_HOST + "/user/starred/" + owner + "/" +repo;
        Map head = new HashMap();
        API.configAuthorizationHead(head, token);
        NetworkResponseRequest responseRequest = new NetworkResponseRequest(Request.Method.GET, url, "", head,  new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                LogUtil.d("RepositoryActionPresentImp", String.valueOf(response.statusCode));
                mUi.onCheckStarStateSuccess(response.statusCode == 204);
                mUi.onRepositoryActionLoaded(ACTION_TYPE_HAS_STAR);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null && error.networkResponse != null && error.networkResponse.statusCode == 404) {
                    mUi.onCheckStarStateSuccess(false);
                }
                mUi.onRepositoryActionLoaded(ACTION_TYPE_HAS_STAR);
            }
        });
        VolleyUtil.addRequest(responseRequest, tag);
    }

    @Override
    public void starRepo(String owner, String repo, String token) {
        mUi.onRepositoryActionLoading(ACTION_TYPE_STAR);
        String url = API.API_HOST + "/user/starred/" + owner + "/" + repo;
        Map head = new HashMap();
        API.configAuthorizationHead(head, token);
        Map<String, String> params = new HashMap<>();
        params.put("star", "star");
        NetworkResponseRequest responseRequest = new NetworkResponseRequest(Request.Method.PUT, url, params, head,  new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                LogUtil.d("RepositoryActionPresentImp", String.valueOf(response.statusCode));
                if (response.statusCode == 204) {
                    mUi.onStarRepoSuccess();
                }else {
                    mUi.onStarRepoError(null);
                }
               mUi.onRepositoryActionLoaded(ACTION_TYPE_STAR);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUi.onStarRepoError(error);
                mUi.onRepositoryActionLoaded(ACTION_TYPE_STAR);
            }
        });
        VolleyUtil.addRequest(responseRequest, tag);
    }

    @Override
    public void unstarRepo(String owner, String repo, String token) {
        mUi.onRepositoryActionLoading(ACTION_TYPE_UNSTAR);
        String url = API.API_HOST + "/user/starred/" + owner + "/" + repo;
        Map head = new HashMap();
        API.configAuthorizationHead(head, token);
        NetworkResponseRequest responseRequest = new NetworkResponseRequest(Request.Method.DELETE, url, "", head,  new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                LogUtil.d("RepositoryActionPresentImp", String.valueOf(response.statusCode));
                if (response.statusCode == 204) {
                    mUi.onUnstarRepoSuccess();
                }else {
                    mUi.onUnstarRepoError(null);
                }
                mUi.onRepositoryActionLoaded(ACTION_TYPE_UNSTAR);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUi.onUnstarRepoError(error);
                mUi.onRepositoryActionLoaded(ACTION_TYPE_UNSTAR);
            }
        });
        VolleyUtil.addRequest(responseRequest, tag);
    }

    @Override
    public void forkRepo(String owner, String repo, String token) {
//        POST /repos/:owner/:repo/forks
//        https://api.github.com/repos/81813780/AVLoadingIndicatorView/forks
        mUi.onRepositoryActionLoading(ACTION_TYPE_FORK);
        String url = API.API_HOST + "/repos/" + owner + "/" + repo + "/forks";
        Map head = new HashMap();
        API.configAuthorizationHead(head, token);
        Map<String, String> params = new HashMap<>();
        NetworkResponseRequest responseRequest = new NetworkResponseRequest(Request.Method.POST, url, " ", head, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                LogUtil.d("RepositoryActionPresentImp", String.valueOf(response.statusCode));
                mUi.onForkRepoSuccess();
                mUi.onRepositoryActionLoaded(ACTION_TYPE_FORK);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUi.onForkRepoError(error);
                mUi.onRepositoryActionLoaded(ACTION_TYPE_FORK);
            }
        });
        VolleyUtil.addRequest(responseRequest, tag);
    }


    @Override
    public void onDeath() {
        VolleyUtil.cancelRequestByTag(tag);
    }
}
