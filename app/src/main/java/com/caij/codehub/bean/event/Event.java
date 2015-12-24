package com.caij.codehub.bean.event;

import com.caij.codehub.bean.Entity;
import com.caij.codehub.bean.Org;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.bean.User;
import com.caij.codehub.utils.EventSpannedUtils;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Caij on 2015/9/24.
 */
public class Event extends Entity {

    public static final String COMMIT_COMMENT = "CommitCommentEvent";

    public static final String CREATE = "CreateEvent";

    public static final String DELETE = "DeleteEvent";

    public static final String DEPLOYMENT = "DeploymentEvent";

    public static final String DEPLOYMENT_STATUS = "DeploymentStatusEvent";

    public static final String ISSUE_COMMENT = "IssueCommentEvent";

    public static final String DOWNLOAD = "DownloadEvent";

    public static final String ISSUES = "IssuesEvent";

    public static final String MEMBER = "MemberEvent";

    public static final String MEMBERSHIP = "MembershipEvent";

    public static final String PULL_REQUEST = "PullRequestEvent";

    public static final String PULL_REQUEST_REVIEW_COMMENT = "PullRequestReviewCommentEvent";

    public static final String PUSH = "PushEvent";

    public static final String REPOSITORY = "RepositoryEvent";

    public static final String TEAM_ADD = "TeamAddEvent";

    public static final String WATCH = "WatchEvent";

    //----------------------------------------------------------------
    public static final String FORK = "ForkEvent";


    /**
     * type : Event
     * public : true
     * payload : {}
     * repo : {"id":3,"name":"octocat/Hello-World","url":"https://api.github.com/repos/octocat/Hello-World"}
     * actor : {"id":1,"login":"octocat","gravatar_id":"","avatar_url":"https://github.com/images/error/octocat_happy.gif","url":"https://api.github.com/users/octocat"}
     * org : {"id":1,"login":"github","gravatar_id":"","url":"https://api.github.com/orgs/github","avatar_url":"https://github.com/images/error/octocat_happy.gif"}
     * created_at : 2011-09-06T17:26:27Z
     * id : 12345
     */

    private String type;
    @SerializedName("public")
    private boolean publicX;
    private Repository repo;
    private User actor;
    private Org org;
    private Date created_at;
    private String id;
    private Object payload;

    private BaseEvent realEvent;

    private EventSpannedUtils.EventBodySpannableStringBuild adapterTitle;

    private String adapterBody;

    public void setType(String type) {
        this.type = type;
    }

    public void setPublicX(boolean publicX) {
        this.publicX = publicX;
    }

    public void setRepo(Repository repo) {
        this.repo = repo;
    }

    public void setActor(User actor) {
        this.actor = actor;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public boolean getPublicX() {
        return publicX;
    }

    public Repository getRepo() {
        return repo;
    }

    public User getActor() {
        return actor;
    }

    public Org getOrg() {
        return org;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public String getId() {
        return id;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public BaseEvent getRealEvent() {
        return realEvent;
    }

    public void setRealEvent(BaseEvent realEvent) {
        this.realEvent = realEvent;
    }

    public EventSpannedUtils.EventBodySpannableStringBuild getAdapterTitle() {
        return adapterTitle;
    }

    public void setAdapterTitle(EventSpannedUtils.EventBodySpannableStringBuild adapterTitle) {
        this.adapterTitle = adapterTitle;
    }

    public String getAdapterBody() {
        return adapterBody;
    }

    public void setAdapterBody(String adapterBody) {
        this.adapterBody = adapterBody;
    }
}