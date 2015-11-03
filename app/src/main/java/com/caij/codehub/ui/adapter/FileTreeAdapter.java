package com.caij.codehub.ui.adapter;

import android.content.Context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_treelist, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();

        onBindViewHolder(viewHolder, position);

        return convertView;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        FileTreeItem treeItem = getItem(position);
        holder.treeItemName.setText(treeItem.getPath());
        if(treeItem.getType().equals(FileTreeItem.MODE_BLOB)){
            holder.treeItemImg.setText(context.getString(R.string.icon_readme));
        }else{
            holder.treeItemImg.setText(context.getString(R.string.icon_owner));
        }
    }

    static class ViewHolder {

        public TextView treeItemImg;
        public TextView treeItemName;


        public ViewHolder(View view){
            treeItemImg = (TextView) view.findViewById(R.id.treeItemImg);
            treeItemName = (TextView) view.findViewById(R.id.treeItemName);
        }
    }
}
