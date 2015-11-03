package com.caij.codehub.ui.listener;

import com.android.volley.VolleyError;
import com.caij.codehub.bean.Repository;

/**
 * Created by Caij on 2015/9/19.
 */
public interface RepositoryInfoUi extends BaseUi {

    public void onGetRepositoryInfoSuccess(Repository repository);

    public void onGetRepositoryInfoError(VolleyError error);

    public void onLoading();

    public void onLoaded();

}
