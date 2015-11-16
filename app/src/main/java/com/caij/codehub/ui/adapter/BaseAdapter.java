package com.caij.codehub.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.caij.codehub.R;
import com.caij.codehub.bean.Entity;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerViewAdapter;
import com.caij.lib.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caij on 2015/9/22.
 */
public abstract class BaseAdapter<E extends Entity> extends LoadMoreRecyclerViewAdapter {

    private static final String TAG = "BaseAdapter";

    private List<E> mEntities;

    public BaseAdapter(Context context) {
        super(context);
    }

    public BaseAdapter(Context context, List<E> entities) {
        super(context);
        mEntities = entities;
    }

    public E getItem(int i) {
        return mEntities.get(i);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setEntities(List<E> entities) {
        mEntities = entities;
    }

    public void addEntities(List<E> entities) {
        if (mEntities == null) {
            mEntities = entities;
        } else {
            mEntities.addAll(entities);
        }
    }

    public List<E> getEntities() {
        return mEntities;
    }

    public void addEntity(E entity) {
        if (mEntities == null) {
            mEntities = new ArrayList<>();
        }
        mEntities.add(entity);
    }

    public void removeEntity(E entiry) {
        if (mEntities != null) {
            mEntities.remove(entiry);
        }
    }

    public void removeEntities(List<E> entities) {
        if (mEntities != null) {
            mEntities.removeAll(entities);
        }
    }

    public void clearEntites() {
        if (mEntities != null) {
            mEntities.clear();
        }
    }

    public int getDataCount() {
        return mEntities == null ? 0 : mEntities.size();
    }

}
