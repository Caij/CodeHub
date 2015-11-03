package com.caij.codehub.ui.listener;

import com.android.volley.VolleyError;
import com.caij.codehub.bean.Comment;

/**
 * Created by Caij on 2015/11/3.
 */
public interface CommentActionUi extends BaseUi{

    public void onCommentSuccess(Comment comment);

    public void onCommentError(VolleyError error);

    public void onCommentLoading();

    public void onCommentLoaded();

}
