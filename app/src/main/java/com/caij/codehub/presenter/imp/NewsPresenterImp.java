package com.caij.codehub.presenter.imp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.News;
import com.caij.codehub.bean.Page;
import com.caij.codehub.presenter.NewsPresenter;
import com.caij.codehub.ui.listener.NewsUi;
import com.caij.lib.utils.GsonUtils;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Caij on 2015/9/24.
 */
public class NewsPresenterImp implements NewsPresenter{

    NewsUi mUi;

    @Override
    public void getReceivedEvents(String username, String token, final int loadType, Page page) {
        mUi.showLoading(loadType);
        String url = API.API_HOST + "/users/" + username + "/received_events";
        Map<String, String> head = new HashMap<>();
        API.configAuthorizationHead(head, token);
        Map<String, String> params = new HashMap<>();
        params.put(API.PAGE, String.valueOf(page.getPageIndex()));
        params.put(API.PER_PAGE, String.valueOf(page.getPageDataCount()));
        GsonRequest<List<News>> request = new GsonRequest<List<News>>(Request.Method.GET, url, null, head, new TypeToken<List<News>>() {}.getType(),new Response.Listener<List<News>>() {
            @Override
            public void onResponse(List<News> response) {
                handlerResponse(loadType, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handlerError(loadType, error);
            }
        });
        VolleyUtil.addRequest(request, this);
    }
    private void handlerError(int loadType, VolleyError error) {
        mUi.hideLoading(loadType);
        mUi.showError(loadType, error);
    }

    private void handlerResponse(int loadType, List<News> newses) {
        mUi.hideLoading(loadType);
        if (loadType == LoadType.REFRESH || loadType == LoadType.FIRSTLOAD) {
            mUi.onGetNewsSuccess(newses);
        }else if (loadType == LoadType.LOADMOER) {
            mUi.onLoadMoreSuccess(newses);
        }
    }


    @Override
    public void attachUi(NewsUi ui) {
        mUi = ui;
    }

    @Override
    public void detachUi(NewsUi ui) {
        VolleyUtil.cancelRequestByTag(this  );
    }
}
