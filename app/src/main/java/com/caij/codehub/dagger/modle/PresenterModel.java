package com.caij.codehub.dagger.modle;

import com.caij.codehub.presenter.LoginPresenter;
import com.caij.codehub.presenter.NewsPresenter;
import com.caij.codehub.presenter.RepositoryInfoPresenter;
import com.caij.codehub.presenter.RepositoryListPresenter;
import com.caij.codehub.presenter.UserListPresenter;
import com.caij.codehub.presenter.UserPresenter;
import com.caij.codehub.presenter.imp.LoginPresenterImp;
import com.caij.codehub.presenter.imp.NewsPresenterImp;
import com.caij.codehub.presenter.imp.RepositoryInfoPresenterImp;
import com.caij.codehub.presenter.imp.RepositoryListPresenterImp;
import com.caij.codehub.presenter.imp.UserListPresenterImp;
import com.caij.codehub.presenter.imp.UserPresenterImp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Caij on 2015/8/26.
 */
@Module
public class PresenterModel {

    @Provides
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenterImp();
    }

    @Provides
    UserPresenter provideUserPresenter() {
        return new UserPresenterImp();
    }

    @Provides
    RepositoryListPresenter provideRepositoryListPresenter() {
        return new RepositoryListPresenterImp();
    }

    @Provides
    RepositoryInfoPresenter provideRepositoryInfoPresenter() {
        return new RepositoryInfoPresenterImp();
    }

    @Provides
    UserListPresenter provideUserListPresenter() {
        return new UserListPresenterImp();
    }

    @Provides
    NewsPresenter provideNewsPresenter() {
        return new NewsPresenterImp();
    }
}
