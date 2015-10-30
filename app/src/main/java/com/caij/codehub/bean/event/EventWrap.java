package com.caij.codehub.bean.event;

import com.caij.codehub.bean.Entity;
import com.caij.codehub.bean.Org;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.bean.User;
import com.caij.lib.utils.GsonUtils;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Caij on 2015/10/30.
 */
public class EventWrap extends Event{

    /**
     * 将Event 转化为EventWrap， 批量应在子线程中
     * @param event
     * @return
     */
    public static EventWrap convert(Event event) {
        BaseEvent realEvent = null;
        if (Event.COMMIT_COMMENT.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), CommitCommentEvent.class);
        }else if (Event.CREATE.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), CreateEvent.class);
        }else if (Event.DELETE.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), DeleteEvent.class);
        }else if (Event.DEPLOYMENT.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), DeploymentEvent.class);
        }else if (Event.DEPLOYMENT_STATUS.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), DeploymentStatusEvent.class);
        }else if (Event.ISSUE_COMMENT.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), IssueCommentEvent.class);
        }else if (Event.ISSUES.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), IssuesEvent.class);
        }else if (Event.MEMBER.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), MemberEvent.class);
        }else if (Event.MEMBERSHIP.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), MembershipEvent.class);
        }else if (Event.PULL_REQUEST.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), PullRequestEvent.class);
        }else if (Event.PULL_REQUEST_REVIEW_COMMENT.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), PullRequestReviewCommentEvent.class);
        }else if (Event.PUSH.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), PushEvent.class);
        }else if (Event.REPOSITORY.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), RepositoryEvent.class);
        }else if (Event.TEAM_ADD.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), TeamAddEvent.class);
        }else if (Event.WATCH.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), WatchEvent.class);
        }
        return new EventWrap(event.getType(), event.getPublicX(), event.getRepo(), event.getActor(), event.getOrg(),
                event.getCreated_at(), event.getId(), realEvent);
    }

    private String type;
    @SerializedName("public")
    private boolean publicX;
    private Repository repo;
    private User actor;
    private Org org;
    private Date created_at;
    private String id;

    private BaseEvent realEvent;

    public EventWrap(String type, boolean publicX, Repository repo, User actor, Org org, Date created_at, String id, BaseEvent realEvent) {
        this.type = type;
        this.publicX = publicX;
        this.repo = repo;
        this.actor = actor;
        this.org = org;
        this.created_at = created_at;
        this.id = id;
        this.realEvent = realEvent;
    }

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

    public BaseEvent getRealEvent() {
        return realEvent;
    }

    public void setRealEvent(BaseEvent realEvent) {
        this.realEvent = realEvent;
    }
}
