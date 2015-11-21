package com.caij.codehub.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caij.codehub.R;
import com.caij.codehub.bean.Issue;
import com.caij.codehub.utils.TimeUtils;
import com.caij.codehub.widgets.recyclerview.RecyclerViewOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/11/3.
 */
public class IssueAdapter extends BaseAdapter<Issue> {

    public IssueAdapter(Context context) {
        this(context, null);
    }

    public IssueAdapter(Context context, List<Issue> entities) {
        super(context, entities);
    }

    public void onBindViewHolderReal(IssueViewHolder viewHolder, int position) {
        Issue issue = getItem(position);
        viewHolder.mTvCommentCount.setText("comments: " + String.valueOf(issue.getComments()));
        viewHolder.mTvIssueNumber.setText(processHtmlString("#" + String.valueOf(issue.getNumber())));
        viewHolder.mTvIssueUpdateTime.setText(context.getString(R.string.update) + ": " + TimeUtils.getRelativeTime(issue.getUpdated_at()));
        viewHolder.mTvIssueState.setText("state: " + issue.getState());
        viewHolder.mTvIssueTitle.setText(processHtmlString(issue.getTitle()));
    }

    private Spanned processHtmlString(String content) {
        StringBuilder builder = new StringBuilder();
        builder.append("<font color=\"#0066B3\">")
                .append(content)
                .append("</font>");
        return Html.fromHtml(builder.toString());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.item_issue, parent, false);
        IssueViewHolder viewHolder = new IssueViewHolder(convertView, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindViewHolderReal((IssueViewHolder) holder, position);
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_issue.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    public static class IssueViewHolder extends ViewHolder{
        @Bind(R.id.tv_issue_number)
        TextView mTvIssueNumber;
        @Bind(R.id.tv_issue_state)
        TextView mTvIssueState;
        @Bind(R.id.tv_comment_count)
        TextView mTvCommentCount;
        @Bind(R.id.tv_issue_update_time)
        TextView mTvIssueUpdateTime;
        @Bind(R.id.tv_issue_title)
        TextView mTvIssueTitle;

        public IssueViewHolder(View itemView, RecyclerViewOnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
            ButterKnife.bind(this, itemView);
        }
    }
}
