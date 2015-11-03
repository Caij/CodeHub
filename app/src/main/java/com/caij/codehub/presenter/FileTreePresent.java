package com.caij.codehub.presenter;

import com.caij.codehub.ui.listener.FileTreeUi;

/**
 * Created by Caij on 2015/11/2.
 */
public interface FileTreePresent extends Present<FileTreeUi> {

    public void loadFileTree(String name, String repo, String ref);

}
