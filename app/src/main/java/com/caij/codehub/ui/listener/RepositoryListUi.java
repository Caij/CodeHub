package com.caij.codehub.ui.listener;

import com.caij.codehub.bean.Repository;
import com.caij.codehub.bean.SearchRepository;

import java.util.List;

/**
 * Created by Caij on 2015/9/18.
 */
public interface RepositoryListUi extends BaseUi{

    public void onRefreshRepositoriesSuccess(List<Repository> repositories);

    public void onLoadMoreRepositoriesSuccess(List<Repository> repositories);
}
