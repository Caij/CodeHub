package com.caij.codehub.interactor;

import com.caij.codehub.interactor.imp.CommentActionInteractorImp;
import com.caij.codehub.interactor.imp.CommentsInteractorImp;
import com.caij.codehub.interactor.imp.FileTreeInteractorImp;
import com.caij.codehub.interactor.imp.IssueListInteractorImp;
import com.caij.codehub.interactor.imp.IssueInteractorImp;
import com.caij.codehub.interactor.imp.AuthenticationInteractorImp;
import com.caij.codehub.interactor.imp.EventsInteractorImp;
import com.caij.codehub.interactor.imp.RepositoryActionInteractorImp;
import com.caij.codehub.interactor.imp.RepositoryInfoInteractorImp;
import com.caij.codehub.interactor.imp.RepositoryListInteractorImp;
import com.caij.codehub.interactor.imp.FollowActionInteractorImp;
import com.caij.codehub.interactor.imp.UserListInteractorImp;
import com.caij.codehub.interactor.imp.UserInteractorImp;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Caij on 2015/10/31.
 */
public class InteractorFactory {

    private static Map<Class, Class> pclazzs = new HashMap<>();

    static {
        pclazzs.put(AuthenticationInteractor.class, AuthenticationInteractorImp.class);
        pclazzs.put(EventsInteractor.class, EventsInteractorImp.class);
        pclazzs.put(RepositoryInfoInteractor.class, RepositoryInfoInteractorImp.class);
        pclazzs.put(RepositoryListInteractor.class, RepositoryListInteractorImp.class);
        pclazzs.put(UserListInteractor.class, UserListInteractorImp.class);
        pclazzs.put(UserInteractor.class, UserInteractorImp.class);
        pclazzs.put(IssueInteractor.class, IssueInteractorImp.class);
        pclazzs.put(CommentsInteractor.class, CommentsInteractorImp.class);
        pclazzs.put(RepositoryActionInteractor.class, RepositoryActionInteractorImp.class);
        pclazzs.put(FollowActionInteractor.class, FollowActionInteractorImp.class);
        pclazzs.put(FileTreeInteractor.class, FileTreeInteractorImp.class);
        pclazzs.put(IssueListInteractor.class, IssueListInteractorImp.class);
        pclazzs.put(CommentActionInteractor.class, CommentActionInteractorImp.class);
    }

    public static <P extends Interactor> P newInteractorInstance(Class<P> iClass) {
        Class<P> tClass = pclazzs.get(iClass);
        if (tClass != null) {
            try {
                return  tClass.newInstance();
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage());
            }
        }else {
            throw new IllegalStateException("unregist Interactor");
        }
    }

}
