package com.caij.codehub.ui.adapter;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.caij.codehub.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/9/18.
 */
public class RepositoryViewHolder {

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


    public RepositoryViewHolder(View itemView, Typeface typeface) {
        ButterKnife.bind(this, itemView);
        tvForkIcon.setTypeface(typeface);
        tvRepositoryIcon.setTypeface(typeface);
        tvStarIcon.setTypeface(typeface);
    }
}
