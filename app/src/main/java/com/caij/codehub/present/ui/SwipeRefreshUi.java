package com.caij.codehub.present.ui;

import java.util.List;

/**
 * Author:  Caij
 * Email:   worldcaij@gmail.com
 * Date:    2015/11/21
 * Description:
 */
public interface SwipeRefreshUi<E> extends BaseUi{

    public void onRefreshSuccess(List<E> entities);

    public void onRefreshError(int msgId);
}
