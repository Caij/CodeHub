package com.caij.codehub.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.caij.codehub.R;
import com.caij.codehub.bean.Comment;
import com.caij.codehub.utils.AvatarUrlUtil;
import com.caij.codehub.utils.CropCircleTransformation;
import com.caij.codehub.utils.TimeUtils;
import com.caij.codehub.widgets.recyclerview.RecyclerViewOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/10/31.
 */
public class CommentAdapter extends BaseAdapter<Comment> {

    private final CropCircleTransformation cropCircleTransformation;
    private AvatarOnClickListener mAvatarOnClickListener;

    public CommentAdapter(Activity activity) {
        this(null, activity);
    }

    public CommentAdapter(List<Comment> entities, Activity activity) {
        super(activity, entities);
        cropCircleTransformation = new CropCircleTransformation(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.item_comment, parent, false);
        RecyclerView.ViewHolder  viewHolder = new CommentViewHolder(convertView, mOnItemClickListener, mAvatarOnClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommentViewHolder) {
            CommentViewHolder viewHolder = (CommentViewHolder) holder;
            Comment comment = getItem(position);

            Glide.with(context).load(AvatarUrlUtil.restoreAvatarUrl(comment.getUser().getAvatar_url())).placeholder(R.drawable.default_circle_head_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).
                    bitmapTransform(cropCircleTransformation).into(viewHolder.avatarImage);
            viewHolder.tvUserName.setText(comment.getUser().getLogin());
            viewHolder.tvCommentBody.setText(comment.getBody());
            viewHolder.tvCommentUpdate.setText(TimeUtils.getRelativeTime(comment.getUpdated_at()));
        }
    }

    public void setAvatarOnClickListener(AvatarOnClickListener avatarOnClickListener) {
        this.mAvatarOnClickListener = avatarOnClickListener;
    }

    public static class CommentViewHolder extends ViewHolder {

        @Bind(R.id.avatar_image)
        ImageView avatarImage;
        @Bind(R.id.tv_user_name)
        TextView tvUserName;
        @Bind(R.id.tv_comment_update)
        TextView tvCommentUpdate;
        @Bind(R.id.tv_comment_body)
        TextView tvCommentBody;

        public CommentViewHolder(View itemView, RecyclerViewOnItemClickListener onItemClickListener, final AvatarOnClickListener avatarOnClickListener) {
            super(itemView, onItemClickListener);
            ButterKnife.bind(this, itemView);
            avatarImage.setOnClickListener(new View.OnClickListener() {
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
