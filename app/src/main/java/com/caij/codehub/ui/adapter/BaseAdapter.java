package com.caij.codehub.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.caij.codehub.bean.Entity;
import com.caij.codehub.widgets.recyclerview.RecyclerViewOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caij on 2015/9/22.
 */
public abstract class BaseAdapter<E extends Entity> extends RecyclerView.Adapter implements IAdapter<E> {

    private static final String TAG = "BaseAdapter";

    private List<E> mEntities;

    protected Context context;

    protected LayoutInflater mInflater;

    protected RecyclerViewOnItemClickListener mOnItemClickListener;

    public BaseAdapter(Context context) {
        this(context, null);
    }

    public BaseAdapter(Context context, List<E> entities) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mEntities = entities;
    }

    public void setOnItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public E getItem(int i) {
        return mEntities.get(i);
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

    @Override
    public int getItemCount() {
        return mEntities == null ? 0 : mEntities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewOnItemClickListener mOnItemClickListener;

        public ViewHolder(View itemView, RecyclerViewOnItemClickListener onItemClickListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.mOnItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }
    }
}
