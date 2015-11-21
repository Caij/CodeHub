package com.caij.codehub.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.caij.codehub.R;
import com.caij.codehub.bean.event.EventWrap;
import com.caij.codehub.utils.CropCircleTransformation;
import com.caij.codehub.utils.TimeUtils;
import com.caij.codehub.widgets.recyclerview.RecyclerViewOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Caij on 2015/9/24.
 */
public class EventsAdapter extends BaseAdapter<EventWrap>{

    private Transformation<Bitmap> mTransformation;
    private AvatarOnClickListener mAvatarOnClickListener;

    public EventsAdapter(Activity activity) {
        this(activity, null);
    }

    public EventsAdapter(Context context, List<EventWrap> entities) {
        super(context, entities);
        mTransformation = new CropCircleTransformation(context);
    }

    public void onBindViewHolderReal(EventViewHolder holder, int position) {
        EventWrap event = getItem(position);
        Glide.with(context).load(event.getActor().getAvatar_url()).placeholder(R.drawable.default_circle_head_image).
                bitmapTransform(mTransformation).into(holder.avatar);
        holder.happenTime.setText(TimeUtils.getRelativeTime(event.getCreated_at()));
        holder.event.setText(event.getAdapterTitle());
        holder.eventBody.setText(event.getAdapterBody());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_event, parent, false);
        EventViewHolder holder = new EventViewHolder(view, mOnItemClickListener, mAvatarOnClickListener);
        return holder;
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
