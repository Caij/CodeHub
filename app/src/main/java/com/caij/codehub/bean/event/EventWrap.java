package com.caij.codehub.bean.event;

import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import com.caij.codehub.bean.Entity;
import com.caij.codehub.bean.Org;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.bean.User;
import com.caij.codehub.utils.EventSpannedUtils;
import com.caij.lib.utils.GsonUtils;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Caij on 2015/10/30.
 */
public class EventWrap extends Entity{

    /**
     * 将Event to EventWrap
     * @param event
     * @return
     */
    public static Event convert(Event event) {
        BaseEvent realEvent = null;

        EventSpannedUtils.EventBodySpannableStringBuild builder = new EventSpannedUtils.EventBodySpannableStringBuild();

        EventSpannedUtils.parseUser(event.getActor().getLogin(), builder);

        String adapterBody = null;
        if (Event.COMMIT_COMMENT.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), CommitCommentEvent.class);
            CommitCommentEvent commitCommentEvent = (CommitCommentEvent) realEvent;
            builder.append(" ").append("commented on commit");
            adapterBody = commitCommentEvent.getComment().getBody();
        }else if (Event.CREATE.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), CreateEvent.class);
            builder.append(" ")
                    .append("create");
            adapterBody = "";
        }else if (Event.DELETE.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), DeleteEvent.class);
            builder.append(" ")
                    .append("delete");
        }else if (Event.DEPLOYMENT.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), DeploymentEvent.class);
            builder.append(" ")
                    .append("deployment");
            adapterBody = "";
        }else if (Event.DEPLOYMENT_STATUS.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), DeploymentStatusEvent.class);
            builder.append(" ")
                    .append("deployment status");
            adapterBody = "";
        }else if (Event.ISSUE_COMMENT.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), IssueCommentEvent.class);
            IssueCommentEvent issueCommentEvent = (IssueCommentEvent) realEvent;
            builder.append(" ").append("comment").append(" on issue ");
            EventSpannedUtils.parseIssueNum("#" + issueCommentEvent.getIssue().getNumber(), builder).append(" in ");
            adapterBody = issueCommentEvent.getComment().getBody();
        }else if (Event.ISSUES.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), IssuesEvent.class);
            IssuesEvent issuesEvent = (IssuesEvent) realEvent;
            builder.append(" ").append(issuesEvent.getAction()).append(" issue ");
            EventSpannedUtils.parseIssueNum("#" + issuesEvent.getIssue().getNumber(), builder).append(" in ");
            adapterBody = issuesEvent.getIssue().getTitle();
        }else if (Event.MEMBER.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), MemberEvent.class);
            builder.append(" ").append("member");
            adapterBody = "";
        }else if (Event.MEMBERSHIP.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), MembershipEvent.class);
            builder.append(" ").append("membership");
            adapterBody = "";
        }else if (Event.PULL_REQUEST.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), PullRequestEvent.class);
            PullRequestEvent pullRequestEvent = (PullRequestEvent) realEvent;
            String[] urlSp = pullRequestEvent.getPull_request().getUrl().split("/");
            String action = "";
            if ("closed".equals(pullRequestEvent.getAction())) {
                action = "merged";
            }else {
                action = pullRequestEvent.getAction();
            }
            builder.append(" ").append(action).
                    append(" pull request ").append(Html.fromHtml(processHtmlString("#" + urlSp[urlSp.length - 1]))).append(" in ");
            adapterBody = pullRequestEvent.getPull_request().getTitle();
        }else if (Event.PULL_REQUEST_REVIEW_COMMENT.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), PullRequestReviewCommentEvent.class);
            builder.append(" ").append("pull request review comment");
            adapterBody = "";
        }else if (Event.PUSH.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), PushEvent.class);
            PushEvent pushEvent = (PushEvent) realEvent;
            builder.append(" ").append("push to ").append(Html.fromHtml(processHtmlString("master"))).append(" at");
            adapterBody = pushEvent.getBase_ref();
        }else if (Event.REPOSITORY.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), RepositoryEvent.class);
            builder.append(" ")
                    .append("repository");
        }else if (Event.TEAM_ADD.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), TeamAddEvent.class);
            builder.append(" ").append("team add");
            adapterBody = "";
        }else if (Event.WATCH.equals(event.getType())) {
            WatchEvent watchEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), WatchEvent.class);
            realEvent = watchEvent;
            builder.append(" ").append(watchEvent.getAction());
            adapterBody = "";
        }else if (Event.FORK.equals(event.getType())) {
            realEvent = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(event.getPayload()), ForkEvent.class);
            builder.append(" ").append("forked");
            adapterBody = "";
        }else {
            builder.append(" unsupport event item");
            adapterBody = "";
        }
        builder.append(" ");
        EventSpannedUtils.parseRepository(event.getRepo().getName(), builder).append(" ");

        event.setRealEvent(realEvent);
        event.setAdapterBody(adapterBody);
        event.setAdapterTitle(builder);

        return event;
    }

    private static String processHtmlString(String content) {
        StringBuilder builder = new StringBuilder();
        builder.append("<font color=\"").append(EventSpannedUtils.LINK_COLOR).append("\">")
                .append(content)
                .append("</font>");
        return builder.toString();
    }
}
