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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/9/24.
 */
public class UserAdapter extends BaseAdapter<User> {

    private final CropCircleTransformation cropCircleTransformation;

    public UserAdapter(Context context, List<User> entities) {
        super(context, entities);
        cropCircleTransformation = new CropCircleTransformation(context);
    }

    @Override
    public void onBindDataViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindViewHolderReal((UserViewHolder) holder, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_user, parent, false);
        UserViewHolder holder= new UserViewHolder(view);
        return holder;
    }

    public void onBindViewHolderReal(UserViewHolder holder, int i) {
        User user = (User) getItem(i);
        holder.tvUserName.setText(user.getLogin());
        Glide.with(context).load(user.getAvatar_url()).placeholder(R.drawable.default_circle_head_image).
                bitmapTransform(cropCircleTransformation).into(holder.imgUserAvatar);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.img_user_avatar)
        ImageView imgUserAvatar;
        @Bind(R.id.tv_user_name)
        TextView tvUserName;

        public UserViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
