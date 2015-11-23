package com.caij.codehub.present.ui;

import java.util.List;

/**
 * Author:  Caij
 * Email:   worldcaij@gmail.com
 * Date:    2015/11/21
 * Description:
 */
public interface RecyclerViewUi<E> extends BaseUi{

    public void onFirstLoadSuccess(List<E> entities);

    public void onFirstLoadError(int msgId);

    public void onLoadMoreSuccess(List<E> entities);

    public void onLoadMoreError(int msgId);

    public void showEmptyView(boolean isVisible);
}
