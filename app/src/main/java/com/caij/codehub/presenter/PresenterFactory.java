package com.caij.codehub.presenter;

import com.caij.codehub.presenter.imp.CommentActionPresentImp;
import com.caij.codehub.presenter.imp.CommentsPresentImp;
import com.caij.codehub.presenter.imp.FileTreePresentImp;
import com.caij.codehub.presenter.imp.IssueListPresentImp;
import com.caij.codehub.presenter.imp.IssuePresentImp;
import com.caij.codehub.presenter.imp.LoginPresenterImp;
import com.caij.codehub.presenter.imp.EventsPresenterImp;
import com.caij.codehub.presenter.imp.RepositoryActionPresentImp;
import com.caij.codehub.presenter.imp.RepositoryInfoPresenterImp;
import com.caij.codehub.presenter.imp.RepositoryListPresenterImp;
import com.caij.codehub.presenter.imp.FollowActionPresentImp;
import com.caij.codehub.presenter.imp.UserListPresenterImp;
import com.caij.codehub.presenter.imp.UserPresenterImp;


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
        pclazzs.put(FollowActionPresent.class, FollowActionPresentImp.class);
        pclazzs.put(FileTreePresent.class, FileTreePresentImp.class);
        pclazzs.put(IssueListPresent.class, IssueListPresentImp.class);
        pclazzs.put(CommentActionPresent.class, CommentActionPresentImp.class);
    }

    public static <P extends Present> P newPresentInstance(Class<P> pClass) {
        Class<P> tClass = pclazzs.get(pClass);
        if (tClass != null) {
            try {
                return  tClass.newInstance();
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage());
            }
        }else {
            throw new IllegalStateException("unregist Present");
        }
    }

}
