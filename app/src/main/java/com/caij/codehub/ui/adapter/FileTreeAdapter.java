package com.caij.codehub.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caij.codehub.R;
import com.caij.codehub.bean.FileTreeItem;

import java.util.List;

/**
 * Created by Caij on 2015/11/2.
 */
public class FileTreeAdapter extends BaseAdapter<FileTreeItem>{

    public FileTreeAdapter(Context context, List<FileTreeItem> entities) {
        super(context, entities);
    }

    @Override
    public void onBindDataViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindViewHolderReal((ViewHolder) holder, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.item_treelist, parent, false);
        ViewHolder viewHolder  = new ViewHolder(convertView);
        return viewHolder;
    }

    public void onBindViewHolderReal(ViewHolder holder, int position) {
        FileTreeItem treeItem = getItem(position);
        holder.treeItemName.setText(treeItem.getPath());
        if(treeItem.getType().equals(FileTreeItem.MODE_BLOB)){
            holder.treeItemImg.setText(context.getString(R.string.icon_readme));
        }else{
            holder.treeItemImg.setText(context.getString(R.string.icon_owner));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView treeItemImg;
        public TextView treeItemName;

        public ViewHolder(View view){
            super(view);
            treeItemImg = (TextView) view.findViewById(R.id.treeItemImg);
            treeItemName = (TextView) view.findViewById(R.id.treeItemName);
        }
    }
}
