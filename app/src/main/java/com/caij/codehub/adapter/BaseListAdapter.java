package com.caij.codehub.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.caij.codehub.bean.Entity;

import java.util.List;

/**
 * Created by Caij on 2015/8/24.
 */
public abstract class BaseListAdapter<T extends Entity> extends android.widget.BaseAdapter{

    protected List<T> mEntities;

    public BaseListAdapter(List<T> entities) {
        mEntities = entities;
    }

    @Override
    public int getCount() {
        return mEntities == null ? 0 : mEntities.size();
    }

    @Override
    public Object getItem(int i) {
        return mEntities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
