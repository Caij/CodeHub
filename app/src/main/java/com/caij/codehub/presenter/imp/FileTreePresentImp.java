package com.caij.codehub.presenter.imp;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.bean.Tree;
import com.caij.codehub.presenter.FileTreePresent;
import com.caij.codehub.ui.listener.FileTreeUi;
import com.caij.lib.utils.VolleyUtil;
import com.caij.lib.volley.request.GsonRequest;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Caij on 2015/11/2.
 */
public class FileTreePresentImp implements FileTreePresent {

    private final FileTreeUi mUi;

    public FileTreePresentImp(FileTreeUi ui){
        this.mUi = ui;
    }

    @Override
    public void loadFileTree(String name, String repo, String ref) {
        mUi.onLoading(LoadType.FIRSTLOAD);
        StringBuilder builder = new StringBuilder();
        builder.append(API.API_HOST).append("/repos/").append(name).append("/").
                append(repo).append("/git/trees/");
        if (!TextUtils.isEmpty(ref)) {
            builder.append(ref);
        }
        String url = builder.toString();
                GsonRequest<Tree> request = new GsonRequest<Tree>(Request.Method.GET, url, new TypeToken<Tree>() {
        }.getType(), new Response.Listener<Tree>() {
            @Override
            public void onResponse(Tree response) {
                mUi.onLoaded(LoadType.FIRSTLOAD);
                mUi.onFirstLoadSuccess(response.getTree());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUi.onLoaded(LoadType.FIRSTLOAD);
                mUi.onFirstLoadError(error);
            }
        });
        VolleyUtil.addRequest(request, null);
    }

    @Override
    public void onDeath() {

    }
}
