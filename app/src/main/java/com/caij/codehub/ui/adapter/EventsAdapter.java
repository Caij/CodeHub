package com.caij.codehub.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.caij.codehub.R;
import com.caij.codehub.bean.event.Event;
import com.caij.codehub.bean.event.EventWrap;
import com.caij.codehub.bean.event.IssueCommentEvent;
import com.caij.codehub.ui.activity.IssueInfoActivity;
import com.caij.codehub.ui.activity.RepositoryInfoActivity;
import com.caij.codehub.ui.activity.UserInfoActivity;
import com.caij.codehub.utils.AvatarUrlUtil;
import com.caij.codehub.utils.CropCircleTransformation;
import com.caij.codehub.utils.EventSpannedUtils;
import com.caij.codehub.utils.TimeUtils;
import com.caij.codehub.widgets.FixClickableSpanBugTextView;
import com.caij.codehub.widgets.recyclerview.RecyclerViewOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Caij on 2015/9/24.
 */
public class EventsAdapter extends BaseAdapter<Event>{

    private final CropCircleTransformation mTransformation;
    private AvatarOnClickListener mAvatarOnClickListener;
    private Activity mActivity;

    public EventsAdapter(Activity activity) {
        this(activity, null);
        mActivity = activity;
    }

    public EventsAdapter(Context context, List<Event> entities) {
        super(context, entities);
        mTransformation = new CropCircleTransformation(context);
    }

    public void onBindViewHolderReal(final EventViewHolder holder, final int position) {
        final Event event = getItem(position);
        Glide.with(context).load(AvatarUrlUtil.restoreAvatarUrl(event.getActor().getAvatar_url())).placeholder(R.drawable.default_circle_head_image).diskCacheStrategy(DiskCacheStrategy.ALL).
                bitmapTransform(mTransformation).into(holder.avatar);
        holder.happenTime.setText(TimeUtils.getRelativeTime(event.getCreated_at()));
        final EventSpannedUtils.EventBodySpannableStringBuild build = event.getAdapterTitle();

        build.setUserClickListener(new EventSpannedUtils.OnClickableSpannedClickListener() {
            @Override
            public void onClick(View view, String content) {
                mAvatarOnClickListener.onAvatarClick(holder.avatar, position);
            }
        });

        if (build.getRepositoryClickListener() == null) {
            build.setRepositoryClickListener(new EventSpannedUtils.OnClickableSpannedClickListener() {
                @Override
                public void onClick(View view, String content) {
                    String[] repoInfo = content.split("/");
                    Intent intent = RepositoryInfoActivity.newInstance(mActivity, repoInfo[0], repoInfo[1]);
                    mActivity.startActivity(intent);
                }
            });
        }

        if (build.isSupportIssueClickListener()) {
            build.setIssueClickListener(new EventSpannedUtils.OnClickableSpannedClickListener() {
                @Override
                public void onClick(View view, String content) {
                    if (Event.ISSUE_COMMENT.equals(event.getType())) {
                        IssueCommentEvent realEvent = (IssueCommentEvent) event.getRealEvent();
                        String[] repoInfo = event.getRepo().getName().split("/");
                        Intent intent = IssueInfoActivity.newIntent(mActivity, repoInfo[0], repoInfo[1],
                                String.valueOf(realEvent.getIssue().getNumber()), realEvent.getIssue().getTitle(), realEvent.getIssue().getBody());
                        mActivity.startActivity(intent);
                    }
                }
            });
        }

        holder.event.setText(build);
        holder.eventBody.setText(event.getAdapterBody());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view, mOnItemClickListener, mAvatarOnClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindViewHolderReal((EventViewHolder) holder, position);
    }

    public void setAvatarOnClickListener(AvatarOnClickListener avatarOnClickListener) {
        this.mAvatarOnClickListener = avatarOnClickListener;
    }

    public static class EventViewHolder extends ViewHolder{

        @Bind(R.id.avatar)
        public ImageView avatar;
        @Bind(R.id.event_body)
        public TextView eventBody;
        @Bind(R.id.event)
        public TextView event;
        @Bind(R.id.happenTime)
        public TextView happenTime;

        public EventViewHolder(View itemView, RecyclerViewOnItemClickListener onItemClickListener, final AvatarOnClickListener avatarOnClickListener) {
            super(itemView, onItemClickListener);
            ButterKnife.bind(this, itemView);
            event.setMovementMethod(FixClickableSpanBugTextView.LocalLinkMovementMethod.getInstance());
            avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (avatarOnClickListener != null) {
                        avatarOnClickListener.onAvatarClick(v, getLayoutPosition());
                    }
                }
            });
        }
    }

}
