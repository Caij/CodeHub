package com.caij.codehub.present;

import com.caij.codehub.bean.Entity;
import com.caij.codehub.present.ui.ListUi;

import java.util.List;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/16.
 */
public abstract class ListPresent<UI extends ListUi<E>, E extends Entity> extends Present<UI>{

    public ListPresent(UI ui) {
        super(ui);
    }

    protected void defaultDealWithError(int msgId, LoadType loadType) {
        ListUi listUi = mUi.get();
        if (listUi != null) {
            switch (loadType) {
                case FIRST:
                    listUi.showContentAnimLoading(false);
                    listUi.showContentError();
                    listUi.onFirstLoadError(msgId);
                    break;

                case REFRESH:
                    listUi.onRefreshError(msgId);
                    break;

                case MORE:
                    listUi.onLoadMoreError(msgId);
                    break;
            }
        }
    }

    protected void defaultDealWithSuccess(List<E> entities, LoadType loadType) {
        ListUi listUi = mUi.get();
        if (listUi != null) {
            switch (loadType) {
                case FIRST:
                    listUi.showContentAnimLoading(false);
                    listUi.onFirstLoadSuccess(entities);
                    if (entities == null || entities.size() == 0) {
                        listUi.showEmptyView(true);
                    } else {
                        listUi.showEmptyView(false);
                    }
                    break;

                case REFRESH:
                    listUi.onRefreshSuccess(entities);
                    if (entities == null || entities.size() == 0) {
                        listUi.showEmptyView(true);
                    } else {
                        listUi.showEmptyView(false);
                    }
                    break;

                case MORE:
                    listUi.onLoadMoreSuccess(entities);
                    break;
            }
        }
    }

    protected void defaultDealWithLoading(LoadType loadType) {
        ListUi listUi = mUi.get();
        if (listUi != null) {
            switch (loadType) {
                case FIRST:
                    listUi.showContentAnimLoading(true);
                    break;

                case REFRESH:
                    break;

                case MORE:
                    break;
            }
        }
    }
}
