package com.caij.codehub.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caij.codehub.R;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.utils.TextTypeFaceUtils;

import java.util.List;

/**
 * Created by Caij on 2015/9/18.
 */
public class RepositoryAdapter extends BaseAdapter<Repository>{


    public RepositoryAdapter(Context context, List<Repository> repositories) {
        super(context, repositories);
    }


    public void onBindViewHolder(RepositoryViewHolder viewHolder, int i) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        RepositoryViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_repository, viewGroup, false);
            holder = new RepositoryViewHolder(view);
            view.setTag(holder);
        }

        holder = (RepositoryViewHolder) view.getTag();

        onBindViewHolder(holder, i);
        return view;
    }
}

