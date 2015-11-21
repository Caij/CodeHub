package com.caij.codehub.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caij.codehub.R;
import com.caij.codehub.bean.FileTreeItem;
import com.caij.codehub.widgets.recyclerview.RecyclerViewOnItemClickListener;

import java.util.List;

/**
 * Created by Caij on 2015/11/2.
 */
public class FileTreeAdapter extends BaseAdapter<FileTreeItem>{

    public FileTreeAdapter(Context context) {
        this(context, null);
    }

    public FileTreeAdapter(Context context, List<FileTreeItem> entities) {
        super(context, entities);
    }

    public void onBindViewHolderReal(FileItemViewHolder holder, int position) {
        FileTreeItem treeItem = getItem(position);
        holder.treeItemName.setText(treeItem.getPath());
        if(treeItem.getType().equals(FileTreeItem.MODE_BLOB)){
            holder.treeItemImg.setText(context.getString(R.string.icon_readme));
        }else{
            holder.treeItemImg.setText(context.getString(R.string.icon_owner));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.item_treelist, parent, false);
        FileItemViewHolder viewHolder  = new FileItemViewHolder(convertView, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindViewHolderReal((FileItemViewHolder) holder, position);
    }

    public static class FileItemViewHolder extends ViewHolder{

        public TextView treeItemImg;
        public TextView treeItemName;

        public FileItemViewHolder(View itemView, RecyclerViewOnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
            treeItemImg = (TextView) itemView.findViewById(R.id.treeItemImg);
            treeItemName = (TextView) itemView.findViewById(R.id.treeItemName);
        }
    }
}
