package com.caij.codehub.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caij.codehub.R;
import com.caij.codehub.bean.Comment;
import com.caij.codehub.utils.CropCircleTransformation;
import com.caij.codehub.utils.TimeUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/10/31.
 */
public class CommentAdapter extends BaseAdapter<Comment> {

    public static final int COMMENT = 2;
    public static final int ISSUE = 3;
    private final CropCircleTransformation cropCircleTransformation;

    private View mIssueContentHeadView;

    public CommentAdapter(List<Comment> entities, Context context) {
        super(context, entities);
        cropCircleTransformation = new CropCircleTransformation(context);
    }

    @Override
    public void onBindDataViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommentViewHolder) {
            CommentViewHolder viewHolder = (CommentViewHolder) holder;
            Comment comment = getItem(position - 1);

            Glide.with(context).load(comment.getUser().getAvatar_url()).placeholder(R.drawable.default_circle_head_image).
                    bitmapTransform(cropCircleTransformation).into(viewHolder.avatarImage);
            viewHolder.tvUserName.setText(comment.getUser().getLogin());
            viewHolder.tvCommentBody.setText(comment.getBody());
            viewHolder.tvCommentUpdate.setText(TimeUtils.getRelativeTime(comment.getUpdated_at()));
        }
    }

    @Override
    public int getDataCount() {
        return super.getDataCount() + 1;
    }

    public void addIssueContentHeadView(View view) {
        mIssueContentHeadView = view;
    }

    @Override
    public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == COMMENT) {
            View convertView = mInflater.inflate(R.layout.item_comment, parent, false);
            viewHolder = new CommentViewHolder(convertView);
        } else {
            viewHolder = new IssueHeadViewHolder(mIssueContentHeadView);
        }
        return viewHolder;
    }

    @Override
    public int getDataViewType(int position) {
        if (position == 0) {
            return ISSUE;
        } else {
            return COMMENT;
        }
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.avatar_image)
        ImageView avatarImage;
        @Bind(R.id.tv_user_name)
        TextView tvUserName;
        @Bind(R.id.tv_comment_update)
        TextView tvCommentUpdate;
        @Bind(R.id.tv_comment_body)
        TextView tvCommentBody;

        public CommentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_issue_head.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class IssueHeadViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_issue_title)
        TextView mTvIssueTitle;
        @Bind(R.id.tv_issue_body)
        TextView mTvIssueBody;

        IssueHeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
