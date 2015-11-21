package com.caij.codehub.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caij.codehub.R;
import com.caij.codehub.bean.User;
import com.caij.codehub.utils.CropCircleTransformation;
import com.caij.codehub.widgets.recyclerview.RecyclerViewOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/9/24.
 */
public class UserAdapter extends BaseAdapter<User> {

    private final CropCircleTransformation cropCircleTransformation;

    public UserAdapter(Context context) {
        this(context, null);
    }

    public UserAdapter(Context context, List<User> entities) {
        super(context, entities);
        cropCircleTransformation = new CropCircleTransformation(context);
    }

    public void onBindViewHolderReal(UserViewHolder holder, int i) {
        User user = getItem(i);
        holder.tvUserName.setText(user.getLogin());
        Glide.with(context).load(user.getAvatar_url()).placeholder(R.drawable.default_circle_head_image).
                bitmapTransform(cropCircleTransformation).into(holder.imgUserAvatar);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_user, parent, false);
        UserViewHolder holder= new UserViewHolder(view, mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindViewHolderReal((UserViewHolder) holder, position);
    }

    public static class UserViewHolder extends ViewHolder{

        @Bind(R.id.img_user_avatar)
        ImageView imgUserAvatar;
        @Bind(R.id.tv_user_name)
        TextView tvUserName;

        public UserViewHolder(View itemView, RecyclerViewOnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
            ButterKnife.bind(this, itemView);
        }
    }
}
