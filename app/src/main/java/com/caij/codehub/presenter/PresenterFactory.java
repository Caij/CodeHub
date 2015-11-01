package com.caij.codehub.presenter;

import com.caij.codehub.presenter.imp.IssuePresentImp;
import com.caij.codehub.presenter.imp.LoginPresenterImp;
import com.caij.codehub.presenter.imp.EventsPresenterImp;
import com.caij.codehub.presenter.imp.RepositoryActionPresentImp;
import com.caij.codehub.presenter.imp.RepositoryInfoPresenterImp;
import com.caij.codehub.presenter.imp.RepositoryListPresenterImp;
import com.caij.codehub.presenter.imp.UserFollowPresentImp;
import com.caij.codehub.presenter.imp.UserListPresenterImp;
import com.caij.codehub.presenter.imp.UserPresenterImp;
import com.caij.codehub.ui.listener.BaseUi;


import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Caij on 2015/10/31.
 * 简易版生成PresentImp，Dagger不熟悉。
 */
public class PresenterFactory {

    private static Map<Class, Class> pclazzs = new HashMap<>();

    static {
        pclazzs.put(LoginPresenter.class, LoginPresenterImp.class);
        pclazzs.put(EventsPresenter.class, EventsPresenterImp.class);
        pclazzs.put(RepositoryInfoPresenter.class, RepositoryInfoPresenterImp.class);
        pclazzs.put(RepositoryListPresenter.class, RepositoryListPresenterImp.class);
        pclazzs.put(UserListPresenter.class, UserListPresenterImp.class);
        pclazzs.put(UserPresenter.class, UserPresenterImp.class);
        pclazzs.put(IssuePresent.class, IssuePresentImp.class);
        pclazzs.put(CommentsPresent.class, CommentsPresentImp.class);
        pclazzs.put(RepositoryActionPresent.class, RepositoryActionPresentImp.class);
        pclazzs.put(UserFollowPresent.class, UserFollowPresentImp.class);
    }

    public static <P extends BasePresent, U extends BaseUi> P newPresentInstance(Class<P> pClass, Class<U> uClazz, U u) {
        Class<P> tClass = pclazzs.get(pClass);
        if (tClass != null) {
            try {
                Constructor<P> constructor = tClass.getConstructor(uClazz);
                return constructor.newInstance(u);
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage());
            }
        }else {
            throw new IllegalStateException("未注册Present");
        }
    }

//    public static  LoginPresenter provideLoginPresenter(LoginUi ui) {
//        return new LoginPresenterImp(ui);
//    }
//
//    public static  EventsPresenter provideNewsPresenter(EventsUi ui) {
//        return new EventsPresenterImp(ui);
//    }
//
//    public static  RepositoryListPresenter provideRepositoryListPresenter(RepositoryListUi ui) {
//        return new RepositoryListPresenterImp(ui);
//    }
//
//    public static  RepositoryInfoPresenter provideRepositoryInfoPresenter(RepositoryInfoUi ui) {
//        return new RepositoryInfoPresenterImp(ui);
//    }
//
//    public static  UserListPresenter provideUserListPresenter(UserListUi ui) {
//        return new UserListPresenterImp(ui);
//    }
//
//    public static  UserPresenter provideUserPresenter(UserUi ui) {
//        return new UserPresenterImp(ui);
//    }

}
