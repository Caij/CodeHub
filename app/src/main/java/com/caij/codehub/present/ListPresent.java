package com.caij.codehub.present;

import com.caij.codehub.bean.Entity;
import com.caij.codehub.present.ui.BaseUi;
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
        mUi.hideLoading();
        if (loadType == LoadType.FIRST) {
            mUi.showErrorView();
        }
        mUi.showError(msgId);
    }

    protected void defaultDealWithSuccess(List<E> entities, LoadType loadType) {
        mUi.hideLoading();
        switch (loadType) {
            case FIRST:
                mUi.onFirstLoadSuccess(entities);
                break;

            case REFRESH:
                mUi.onRefreshSuccess(entities);
                break;

            case MORE:
                mUi.onLoadMoreSuccess(entities);
                break;
        }
    }

    protected void defaultDealWithLoading(LoadType loadType) {
        switch (loadType) {
            case FIRST:
                mUi.showLoading();
                break;

            case REFRESH:
                break;

            case MORE:
                break;
        }
    }
}
