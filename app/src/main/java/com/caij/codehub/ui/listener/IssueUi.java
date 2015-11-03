package com.caij.codehub.ui.listener;

import com.android.volley.VolleyError;
import com.caij.codehub.bean.Issue;

/**
 * Created by Caij on 2015/10/31.
 */
public interface IssueUi extends BaseUi{

    public void onGetIssueSuccess(Issue issue);

    public void onGetIssueError(VolleyError error);
}
