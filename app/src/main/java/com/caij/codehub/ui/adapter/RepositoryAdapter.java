package com.caij.codehub.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caij.codehub.R;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.widgets.recyclerview.RecyclerViewOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/9/18.
 */
public class RepositoryAdapter extends BaseAdapter<Repository>{

    public RepositoryAdapter(Context context) {
        this(context, null);
    }

    public RepositoryAdapter(Context context, List<Repository> repositories) {
        super(context, repositories);
    }

    public void onBindViewHolderReal(RepositoryViewHolder viewHolder, int i) {
        Repository repository = getItem(i);
        viewHolder.tvStarSum.setText(String.valueOf(repository.getStargazers_count()));
        viewHolder.tvForkSum.setText(String.valueOf(repository.getForks_count()));
        viewHolder.tvRepositoryName.setText(repository.getName());
        if (TextUtils.isEmpty(repository.getDescription())) {
            viewHolder.tvRepositoryDesc.setVisibility(View.GONE);
        }else {
            viewHolder.tvRepositoryDesc.setVisibility(View.VISIBLE);
            viewHolder.tvRepositoryDesc.setText(repository.getDescription());
        }
        if(repository.isFork()){
            viewHolder.tvRepositoryIcon.setText(R.string.icon_fork);
        }else{
            viewHolder.tvRepositoryIcon.setText(R.string.icon_repo);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_repository, parent, false);
        RepositoryViewHolder holder = new RepositoryViewHolder(view, mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindViewHolderReal((RepositoryViewHolder) holder, position);
    }


    public static class RepositoryViewHolder extends ViewHolder{

        @Bind(R.id.tv_repository_icon)
        TextView tvRepositoryIcon;
        @Bind(R.id.tv_repository_name)
        TextView tvRepositoryName;
        @Bind(R.id.tv_star_icon)
        TextView tvStarIcon;
        @Bind(R.id.tv_star_sum)
        TextView tvStarSum;
        @Bind(R.id.tv_fork_icon)
        TextView tvForkIcon;
        @Bind(R.id.tv_fork_sum)
        TextView tvForkSum;
        @Bind(R.id.tv_repository_desc)
        TextView tvRepositoryDesc;

        public RepositoryViewHolder(View itemView, RecyclerViewOnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
            ButterKnife.bind(this, itemView);
        }
    }
}

