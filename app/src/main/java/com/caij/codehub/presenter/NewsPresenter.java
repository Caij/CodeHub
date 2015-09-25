package com.caij.codehub.presenter;

import com.caij.codehub.bean.Page;
import com.caij.codehub.ui.listener.NewsUi;

/**
 * Created by Caij on 2015/9/24.
 */
public interface NewsPresenter extends BasePresent<NewsUi>{

    public void getReceivedEvents(String username, String token, int loadType, Page page);

}
