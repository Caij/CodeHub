package com.caij.codehub.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.caij.codehub.API;
import com.caij.codehub.Constant;
import com.caij.codehub.bean.FileTreeItem;
import com.caij.codehub.present.FileTreePresent;
import com.caij.codehub.ui.activity.WebActivity;
import com.caij.codehub.ui.adapter.BaseAdapter;
import com.caij.codehub.ui.adapter.FileTreeAdapter;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/18.
 */
public class FileTreeFragment extends SwipeRefreshRecyclerViewFragment<FileTreeItem>{

    private FileTreePresent mFileTreePresent;
    private String mOwner;
    private String mRepoName;
    private String mSha;

    public static FileTreeFragment newInstance(String owner, String repo, String sha) {
        FileTreeFragment fileTreeFragment = new FileTreeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.USER_NAME, owner);
        bundle.putString(Constant.REPO_NAME, repo);
        bundle.putString(Constant.REPO_SHA, sha);
        fileTreeFragment.setArguments(bundle);
        return fileTreeFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getLoadMoreRecyclerView().setLoadMoreEnable(false);

        Bundle bundle = getArguments();
        mOwner = bundle.getString(Constant.USER_NAME);
        mRepoName = bundle.getString(Constant.REPO_NAME);
        mSha = bundle.getString(Constant.REPO_SHA);

        mFileTreePresent = new FileTreePresent(this);

    }

    @Override
    protected BaseAdapter<FileTreeItem> createRecyclerViewAdapter() {
        return  new FileTreeAdapter(getActivity());
    }

    @Override
    protected RecyclerView.LayoutManager createRecyclerViewLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void onUserFirstVisible() {
        mFileTreePresent.loadFileTree(mOwner, mRepoName, mSha);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mFileTreePresent.loadFileTree(mOwner, mRepoName, mSha);
    }

    @Override
    public void onItemClick(View view, int position) {
        FileTreeItem treeItem =getRecyclerViewAdapter().getItem(position);
        if (treeItem.getType().equals(FileTreeItem.MODE_TREE)) {
            intoItem(treeItem);
        } else if (treeItem.getType().equals(FileTreeItem.MODE_BLOB)) {
            toFile(treeItem);
        }
    }

    private void intoItem(FileTreeItem treeItem) {
        if (getActivity() instanceof LinearBreadcrumbInterface) {
            LinearBreadcrumbInterface linearBreadcrumbInterface = (LinearBreadcrumbInterface) getActivity();
            linearBreadcrumbInterface.intoItem(treeItem);
        }
    }

    private void toFile(FileTreeItem treeItem) {
        if (getActivity() instanceof LinearBreadcrumbInterface) {
            LinearBreadcrumbInterface linearBreadcrumbInterface = (LinearBreadcrumbInterface) getActivity();
            String path = linearBreadcrumbInterface.getAbsolutePath();
            String filePath = path + "/" + treeItem.getPath();
            String url = String.format(API.GITHUB_FILE, mOwner, mRepoName, treeItem.getType(), filePath);
            Intent intent = WebActivity.newIntent(getActivity(), treeItem.getPath(), url);
            startActivity(intent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFileTreePresent.onDeath();
    }


    public static interface LinearBreadcrumbInterface {
        public void intoItem(FileTreeItem item);
        public String getAbsolutePath();
    }
}
