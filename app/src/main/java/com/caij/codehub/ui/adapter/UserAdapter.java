package com.caij.codehub.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caij.codehub.R;
import com.caij.codehub.bean.User;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Caij on 2015/9/24.
 */
public class UserAdapter extends BaseAdapter<User> {

    private final CropCircleTransformation cropCircleTransformation;
    private Context context;

    public UserAdapter(Context context, List<User> entities) {
        super(entities);
        cropCircleTransformation = new CropCircleTransformation(context);
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        UserViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.item_user, null);
            holder= new UserViewHolder(view);
            view.setTag(holder);
        }

        holder = (UserViewHolder) view.getTag();

        onBindViewHolder(holder, i);
        return view;
    }

    private void onBindViewHolder(UserViewHolder holder, int i) {
        User user = (User) getItem(i);
        holder.tvUserName.setText(user.getLogin());
        Glide.with(context).load(user.getAvatar_url()).placeholder(R.drawable.default_circle_head_image).
                bitmapTransform(cropCircleTransformation).into(holder.imgUserAvatar);
    }

    static class UserViewHolder {

        @Bind(R.id.img_user_avatar)
        ImageView imgUserAvatar;
        @Bind(R.id.tv_user_name)
        TextView tvUserName;

        public UserViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
