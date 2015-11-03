package com.caij.codehub.ui.listener;

import com.android.volley.VolleyError;

/**
 * Created by Caij on 2015/10/31.
 */
public interface RepositoryActionUi extends BaseUi {

    public void onStarRepoSuccess();

    public void onStarRepoError(VolleyError error);

    public void onUnstarRepoSuccess();

    public void  onUnstarRepoError(VolleyError error);

    public void onForkRepoSuccess();

    public void  onForkRepoError(VolleyError error);

    public void onCheckStarStateSuccess(boolean isStar);

    public void onRepositoryActionLoading(int actionType);

    public void onRepositoryActionLoaded(int actionType);
}
