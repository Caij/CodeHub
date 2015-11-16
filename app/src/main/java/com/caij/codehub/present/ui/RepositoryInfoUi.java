package com.caij.codehub.present.ui;

import com.caij.codehub.bean.Repository;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/16.
 */
public interface RepositoryInfoUi extends BaseUi{

    public void getRepositoryInfoSuccess(Repository repository);

    public void getStarStateSuccess(boolean isStar);

    public void starSuccess();

    public void unstarSuccess();

    public void forkSuccess();

}
