package com.caij.codehub.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.caij.codehub.R;
import com.caij.codehub.bean.User;
import com.caij.codehub.bean.event.EventWrap;
import com.caij.codehub.ui.activity.UserInfoActivity;
import com.caij.codehub.utils.CropCircleTransformation;
import com.caij.codehub.utils.TimeUtils;

import java.util.List;


/**
 * Created by Caij on 2015/9/24.
 */
public class EventsAdapter extends BaseAdapter<EventWrap>{

    private static final int CLICK_LISTENER = 100;
    private Transformation<Bitmap> mTransformation;

    private Activity mActivity;

    public EventsAdapter(Activity activity) {
        this(activity, null);
        mActivity = activity;
    }

    public EventsAdapter(Context context, List<EventWrap> entities) {
        super(context, entities);
        mTransformation = new CropCircleTransformation(context);
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
                bitmapTransform(mTransformation).into(holder.avatar);
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
