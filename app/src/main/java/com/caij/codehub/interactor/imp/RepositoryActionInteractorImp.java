package com.caij.codehub.interactor.imp;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.interactor.RepositoryActionInteractor;
import com.caij.codehub.interactor.InteractorCallBack;
import com.caij.lib.utils.VolleyManager;
import com.caij.lib.volley.request.NetworkResponseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Caij on 2015/10/31.
 */
public class RepositoryActionInteractorImp implements RepositoryActionInteractor {

    @Override
    public void hasStarRepo(String owner, String repo, String token, Object requestTag, final InteractorCallBack<Boolean> interactorCallBack) {
        interactorCallBack.onLoading();
        String url = API.API_HOST + "/user/starred/" + owner + "/" +repo;
        Map head = new HashMap();
        API.configAuthorizationHead(head, token);
        NetworkResponseRequest responseRequest = new NetworkResponseRequest(Request.Method.GET, url, "", head,  new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                interactorCallBack.onSuccess(response.statusCode == 204);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null && error.networkResponse != null && error.networkResponse.statusCode == 404) {
                    interactorCallBack.onSuccess(false);
                }else {
                    interactorCallBack.onError(error);
                }
            }
        });
        VolleyManager.addRequest(responseRequest, requestTag);
    }

    @Override
    public void starRepo(String owner, String repo, String token, Object requestTag, final InteractorCallBack<NetworkResponse> interactorCallBack) {
        interactorCallBack.onLoading();
        String url = API.API_HOST + "/user/starred/" + owner + "/" + repo;
        Map head = new HashMap();
        API.configAuthorizationHead(head, token);
        Map<String, String> params = new HashMap<>();
        params.put("star", "star");
        NetworkResponseRequest responseRequest = new NetworkResponseRequest(Request.Method.PUT, url, params, head,  new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                if (response.statusCode == 204) {
                    interactorCallBack.onSuccess(response);
                }else {
                    interactorCallBack.onError(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                interactorCallBack.onError(error);
            }
        });
        VolleyManager.addRequest(responseRequest, requestTag);
    }

    @Override
    public void unstarRepo(String owner, String repo, String token, Object requestTag, final InteractorCallBack<NetworkResponse> interactorCallBack) {
        interactorCallBack.onLoading();
        String url = API.API_HOST + "/user/starred/" + owner + "/" + repo;
        Map head = new HashMap();
        API.configAuthorizationHead(head, token);
        NetworkResponseRequest responseRequest = new NetworkResponseRequest(Request.Method.DELETE, url, "", head,  new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                if (response.statusCode == 204) {
                    interactorCallBack.onSuccess(response);
                }else {
                    interactorCallBack.onError(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                interactorCallBack.onError(error);
            }
        });
        VolleyManager.addRequest(responseRequest, requestTag);
    }

    @Override
    public void forkRepo(String owner, String repo, String token, Object requestTag, final InteractorCallBack<NetworkResponse> interactorCallBack) {
//        POST /repos/:owner/:repo/forks
//        https://api.github.com/repos/81813780/AVLoadingIndicatorView/forks
        interactorCallBack.onLoading();
        String url = API.API_HOST + "/repos/" + owner + "/" + repo + "/forks";
        Map head = new HashMap();
        API.configAuthorizationHead(head, token);
        NetworkResponseRequest responseRequest = new NetworkResponseRequest(Request.Method.POST, url, " ", head, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                if (response.statusCode == 204) {
                    interactorCallBack.onSuccess(response);
                }else {
                    interactorCallBack.onError(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                interactorCallBack.onError(error);
            }
        });
        VolleyManager.addRequest(responseRequest, requestTag);
    }
}
