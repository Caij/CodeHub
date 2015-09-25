package com.caij.codehub.ui.listener;

import com.caij.codehub.bean.News;

import java.util.List;

/**
 * Created by Caij on 2015/9/24.
 */
public interface NewsUi extends BaseUi{

    public void onGetNewsSuccess(List<News> newses);

    public void onLoadMoreSuccess(List<News> newses);
}
