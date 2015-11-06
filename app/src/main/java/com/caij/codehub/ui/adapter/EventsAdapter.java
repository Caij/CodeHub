package com.caij.codehub.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Caij on 2015/9/24.
 */
public class EventsAdapter extends BaseAdapter<EventWrap>{

    public EventsAdapter(Context context, List<EventWrap> entities) {
        super(context, entities);
    }

    @Override
    public void onBindDataViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindViewHolderReal((ViewHolder) holder, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_event, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void onBindViewHolderReal(ViewHolder holder, int position) {
        EventWrap event = getItem(position);
        Glide.with(context).load(event.getActor().getAvatar_url()).placeholder(R.drawable.default_circle_head_image).
                bitmapTransform(new CropCircleTransformation(context)).into(holder.avatar);
        holder.happenTime.setText(TimeUtils.getRelativeTime(event.getCreated_at()));
        holder.event.setText(event.getAdapterTitle());
        holder.eventBody.setText(event.getAdapterBody());
    }




    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView avatar;
        public TextView eventBody;
        public TextView event;
        public TextView happenTime;

        public ViewHolder(View view){
            super(view);
            avatar = (ImageView) view.findViewById(R.id.avatar);
            eventBody = (TextView) view.findViewById(R.id.event_body);
            happenTime = (TextView) view.findViewById(R.id.happenTime);
            event = (TextView) view.findViewById(R.id.event);
        }
    }

}
