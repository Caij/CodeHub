package com.caij.codehub.dagger.component;

import com.caij.codehub.dagger.modle.PresenterModel;
import com.caij.codehub.presenter.LoginPresenter;
import com.caij.codehub.presenter.NewsPresenter;
import com.caij.codehub.presenter.RepositoryInfoPresenter;
import com.caij.codehub.presenter.RepositoryListPresenter;
import com.caij.codehub.presenter.UserListPresenter;
import com.caij.codehub.presenter.UserPresenter;

import dagger.Component;

/**
 * Created by Caij on 2015/8/26.
 */
@Component(modules = {PresenterModel.class})
public interface PresenterComponent {
    LoginPresenter provideLoginPresenter();

    UserPresenter provideUserPresenter();

    RepositoryListPresenter provideRepositoryListPresenter();

    RepositoryInfoPresenter provideRepositoryInfoPresenter();

    UserListPresenter provideUserListPresenter();

    NewsPresenter prvideNewsPresenter();
}
