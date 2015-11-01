package com.caij.codehub.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caij.codehub.R;
import com.caij.codehub.bean.Comment;
import com.caij.codehub.utils.TimeUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Caij on 2015/10/31.
 */
public class CommentAdapter extends BaseAdapter<Comment> {

    private final LayoutInflater inflater;
    private final Context content;

    public CommentAdapter(List<Comment> entities, Context context) {
        super(entities);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.content = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_comment, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();

        bindViewHolder(viewHolder, position);

        return convertView;
    }

    private void bindViewHolder(ViewHolder viewHolder, int position) {
        Comment comment = getItem(position);

        Glide.with(content).load(comment.getUser().getAvatar_url()).placeholder(R.drawable.default_circle_head_image).
                bitmapTransform(new CropCircleTransformation(content)).into(viewHolder.avatarImage);
        viewHolder.tvUserName.setText(comment.getUser().getLogin());
        viewHolder.tvCommentBody.setText(comment.getBody());
        viewHolder.tvCommentUpdate.setText(TimeUtils.getRelativeTime(comment.getUpdated_at()));
    }

    static class ViewHolder {

        @Bind(R.id.avatar_image)
        ImageView avatarImage;
        @Bind(R.id.tv_user_name)
        TextView tvUserName;
        @Bind(R.id.tv_comment_update)
        TextView tvCommentUpdate;
        @Bind(R.id.tv_comment_body)
        TextView tvCommentBody;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
