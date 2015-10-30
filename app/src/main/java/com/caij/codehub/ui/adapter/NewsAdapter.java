package com.caij.codehub.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caij.codehub.R;
import com.caij.codehub.bean.event.CommitCommentEvent;
import com.caij.codehub.bean.event.CreateEvent;
import com.caij.codehub.bean.event.DeleteEvent;
import com.caij.codehub.bean.event.DeploymentEvent;
import com.caij.codehub.bean.event.DeploymentStatusEvent;
import com.caij.codehub.bean.event.Event;
import com.caij.codehub.bean.event.EventWrap;
import com.caij.codehub.bean.event.IssueCommentEvent;
import com.caij.codehub.bean.event.IssuesEvent;
import com.caij.codehub.bean.event.MemberEvent;
import com.caij.codehub.bean.event.MembershipEvent;
import com.caij.codehub.bean.event.PullRequestEvent;
import com.caij.codehub.bean.event.PullRequestReviewCommentEvent;
import com.caij.codehub.bean.event.PushEvent;
import com.caij.codehub.bean.event.RepositoryEvent;
import com.caij.codehub.bean.event.TeamAddEvent;
import com.caij.codehub.bean.event.WatchEvent;
import com.caij.codehub.utils.TimeUtils;
import com.caij.lib.utils.GsonUtils;

import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Caij on 2015/9/24.
 */
public class NewsAdapter extends BaseAdapter<EventWrap>{


    private LayoutInflater mInflater;
    private Context context;

    public NewsAdapter(Context context, List<EventWrap> entities) {
        super(entities);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_event, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder = (ViewHolder) view.getTag();

        onBindViewHolder(holder, i);
        return view;
    }

    private void onBindViewHolder(ViewHolder holder, int position) {
        EventWrap event = getItem(position);
        Glide.with(context).load(event.getActor().getAvatar_url()).bitmapTransform(new CropCircleTransformation(context)).into(holder.avatar);
        holder.happenTime.setText(TimeUtils.getRelativeTime(event.getCreated_at()));

        StringBuilder builder = new StringBuilder();
        builder.append(processHtmlString(event.getActor().getLogin()));

        if (Event.COMMIT_COMMENT.equals(event.getType())) {
            CommitCommentEvent realEvent = (CommitCommentEvent) event.getRealEvent();
            builder.append(" ")
                    .append("commit comment");
        }else if (Event.CREATE.equals(event.getType())) {
            CreateEvent realEvent = (CreateEvent) event.getRealEvent();
            builder.append(" ")
                    .append("create");
        }else if (Event.DELETE.equals(event.getType())) {
            DeleteEvent realEvent = (DeleteEvent) event.getRealEvent();
            builder.append(" ")
                    .append("delete");
        }else if (Event.DEPLOYMENT.equals(event.getType())) {
            DeploymentEvent realEvent = (DeploymentEvent) event.getRealEvent();
            builder.append(" ")
                    .append("deployment");
        }else if (Event.DEPLOYMENT_STATUS.equals(event.getType())) {
            DeploymentStatusEvent realEvent = (DeploymentStatusEvent) event.getRealEvent();
            builder.append(" ")
                    .append("deployment status");
        }else if (Event.ISSUE_COMMENT.equals(event.getType())) {
            IssueCommentEvent realEvent = (IssueCommentEvent) event.getRealEvent();
            String[] urlSp = realEvent.getIssue().getUrl().split("/");
            builder.append(" ").append("comment")
                    .append(" on issue ")
                    .append(processHtmlString("#" + urlSp[urlSp.length - 1]));
        }else if (Event.ISSUES.equals(event.getType())) {
            IssuesEvent realEvent = (IssuesEvent) event.getRealEvent();
            String[] urlSp = realEvent.getIssue().getUrl().split("/");
            builder.append(" ").append(realEvent.getAction()).append(" issue ")
                    .append(processHtmlString("#" + urlSp[urlSp.length - 1]));
        }else if (Event.MEMBER.equals(event.getType())) {
            MemberEvent realEvent = (MemberEvent) event.getRealEvent();
            builder.append(" ")
                    .append("member");
        }else if (Event.MEMBERSHIP.equals(event.getType())) {
            MembershipEvent realEvent = (MembershipEvent) event.getRealEvent();
            builder.append(" ")
                    .append("membership");
        }else if (Event.PULL_REQUEST.equals(event.getType())) {
            PullRequestEvent realEvent = (PullRequestEvent) event.getRealEvent();
            String[] urlSp = realEvent.getPull_request().getUrl().split("/");
            builder.append(" ").append(realEvent.getAction()).
                    append(" pull request ").append(processHtmlString("#" + urlSp[urlSp.length - 1]));
        }else if (Event.PULL_REQUEST_REVIEW_COMMENT.equals(event.getType())) {
            PullRequestReviewCommentEvent realEvent = (PullRequestReviewCommentEvent) event.getRealEvent();
            builder.append(" ")
                    .append("pull request review comment");
        }else if (Event.PUSH.equals(event.getType())) {
            PushEvent realEvent = (PushEvent) event.getRealEvent();
            builder.append(" ")
                    .append("push to ").append(processHtmlString("master"));
        }else if (Event.REPOSITORY.equals(event.getType())) {
            RepositoryEvent realEvent = (RepositoryEvent) event.getRealEvent();
            builder.append(" ")
                    .append("repository");
        }else if (Event.TEAM_ADD.equals(event.getType())) {
            TeamAddEvent realEvent = (TeamAddEvent) event.getRealEvent();
            builder.append(" ")
                    .append("team add");
        }else if (Event.WATCH.equals(event.getType())) {
            WatchEvent realEvent = (WatchEvent) event.getRealEvent();
            builder.append(" ")
                    .append("watch");
        }
        builder.append(" in ").append(processHtmlString(event.getRepo().getName()));
        holder.event.setText(Html.fromHtml(builder.toString()));
    }

    private String processHtmlString(String content) {
        StringBuilder builder = new StringBuilder();
        builder.append("<font color=\"#0066B3\">")
                .append(content)
                .append("</font>");
        return builder.toString();
    }

//    public String getPureEventType(String eventType){
//        if(eventType.equals(Event.WATCH)){
//            return "watch";
//        }else if(eventType.equals(Event.FORK)){
//            return "forked";
//        }else if(eventType.equals(Event.CREATE)){
//            return "created repo";
//        }else if(eventType.equals(Event.PULL_REQUEST)){
//            return "opened pull request";
//        }if(eventType.equals(Event.ISSUE_COMMENT)){
//            return "commented on issue";
//        }if(eventType.equals(Event.ISSUES)){
//            return "open issue";
//        }else{
//            return "XXXXX";   // I will add more eventtype later
//        }
//    }


    public static class ViewHolder {

        public ImageView avatar;
        public TextView eventType;
        public TextView event;
        public TextView happenTime;

        public ViewHolder(View view){
            avatar = (ImageView) view.findViewById(R.id.avatar);
            eventType = (TextView) view.findViewById(R.id.eventType);
            happenTime = (TextView) view.findViewById(R.id.happenTime);
            event = (TextView) view.findViewById(R.id.event);

        }

    }


}
