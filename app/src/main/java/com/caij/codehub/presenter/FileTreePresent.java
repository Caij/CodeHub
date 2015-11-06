package com.caij.codehub.presenter;

import com.caij.codehub.bean.Tree;
import com.caij.codehub.ui.callback.UiCallBack;

/**
 * Created by Caij on 2015/11/2.
 */
public interface FileTreePresent extends Present {

    public void loadFileTree(String name, String repo, String ref, Object requestTag, UiCallBack<Tree> uiCallBack);

}
