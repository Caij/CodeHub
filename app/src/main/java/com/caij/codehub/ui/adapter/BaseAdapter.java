package com.caij.codehub.ui.adapter;


import com.caij.codehub.bean.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caij on 2015/9/22.
 */
public abstract class BaseAdapter<E extends Entity> extends android.widget.BaseAdapter{

    public List<E> mEntities;


    public BaseAdapter(List<E> entities) {
        mEntities = entities;
    }

    @Override
    public int getCount() {
        return mEntities == null ? 0 : mEntities.size();
    }

    @Override
    public E getItem(int i) {
        return mEntities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void setEntities(List<E> entities) {
        mEntities = entities;
    }

    public void addEntities(List<E> entities) {
        if (mEntities == null) {
            mEntities = entities;
        }else {
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
}
