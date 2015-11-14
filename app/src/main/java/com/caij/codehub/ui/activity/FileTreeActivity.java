package com.caij.codehub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.android.volley.VolleyError;
import com.caij.codehub.API;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.FileTreeItem;
import com.caij.codehub.bean.Tree;
import com.caij.codehub.presenter.FileTreePresent;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.ui.adapter.BaseAdapter;
import com.caij.codehub.ui.adapter.FileTreeAdapter;
import com.caij.codehub.ui.callback.DefaultUiCallBack;
import com.caij.codehub.ui.callback.UiCallBack;
import com.caij.codehub.widgets.LinearBreadcrumb;
import com.caij.lib.utils.LogUtil;

import butterknife.Bind;

/**
 * Created by Caij on 2015/11/2.
 */
public class FileTreeActivity extends SwipeRefreshRecyclerViewActivity<FileTreeItem> implements LinearBreadcrumb.SelectionCallback {

    private FileTreePresent fileTreePresent;
    private String mOwner;
    private String mRepoName;
    private String mSha;
    private String mBran;

    private UiCallBack<Tree> mUiCallBack;

    public static Intent newIntent(Activity activity, String owner, String repo, String bran) {
        Intent intent = new Intent(activity, FileTreeActivity.class);
        intent.putExtra(Constant.USER_NAME, owner);
        intent.putExtra(Constant.REPO_NAME, repo);
        intent.putExtra(Constant.REPO_BRAN, bran);
        return intent;
    }

    private static final String TAG = "FileTreeActivity";
    @Bind(R.id.bread_crumbs)
    LinearBreadcrumb breadCrumbs;

    @Override
    protected int getAttachLayoutId() {
        return R.layout.activity_file_tree;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.source));
        getLoadMoreRecyclerView().setLoadMoreEnable(false);
        breadCrumbs.initRootCrumb();
        breadCrumbs.setCallback(this);

        mOwner = getIntent().getStringExtra(Constant.USER_NAME);
        mRepoName = getIntent().getStringExtra(Constant.REPO_NAME);
        mSha =  getIntent().getStringExtra(Constant.REPO_BRAN);
        mBran =  getIntent().getStringExtra(Constant.REPO_BRAN);

        initTreeDataCallback();
        fileTreePresent = PresenterFactory.newPresentInstance(FileTreePresent.class);
        fileTreePresent.loadFileTree(mOwner, mRepoName, mSha, getRequestTag(), mUiCallBack);
    }



    @Override
    protected BaseAdapter<FileTreeItem> createRecyclerViewAdapter() {
        return new FileTreeAdapter(this, null);
    }

    @Override
    protected RecyclerView.LayoutManager createRecyclerViewLayoutManager() {
        return new LinearLayoutManager(this);
    }

    public String getAbosolutePath() {
        return breadCrumbs.getAbsolutePath(breadCrumbs.getCrumb(breadCrumbs.size() - 1),"/");
    }

    @Override
    public void onCrumbSelection(LinearBreadcrumb.Crumb crumb, String absolutePath, int count, int index) {
        LogUtil.i(TAG, "crumb = " + crumb);
        LogUtil.i(TAG, "absolutePath = " + absolutePath);
        LogUtil.i(TAG, "count = " + count);
        LogUtil.i(TAG, "index = " + index);
        for (int i = index + 1; i < count; i++) {
            breadCrumbs.removeCrumbAt(breadCrumbs.size() - 1);
        }
        breadCrumbs.setActive(crumb);

        getRecyclerViewAdapter().clearEntites();
        getRecyclerViewAdapter().notifyDataSetChanged();

        this.mSha = crumb.getmAttachMsg();
        fileTreePresent.loadFileTree(mOwner, mRepoName, mSha, getRequestTag(), mUiCallBack);
    }


    public void intoItem(FileTreeItem item) {
        mSha = item.getSha();
        getRecyclerViewAdapter().clearEntites();
        getRecyclerViewAdapter().notifyDataSetChanged();
        breadCrumbs.addCrumb(new LinearBreadcrumb.Crumb(item.getPath(), item.getSha()), true);
        fileTreePresent.loadFileTree(mOwner, mRepoName, mSha, getRequestTag(), mUiCallBack);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        fileTreePresent.loadFileTree(mOwner, mRepoName, mSha, getRequestTag(), mUiCallBack);
    }

    @Override
    public void onBackPressed() {
        String path = getAbosolutePath();
        if (TextUtils.isEmpty(path)) {
            super.onBackPressed();
        }else {
            for (int i = 0; i < breadCrumbs.size() ; i++) {
               LogUtil.d(TAG, breadCrumbs.getCrumb(i).getPath() + "/" + breadCrumbs.getCrumb(i).getmAttachMsg());
            }

            LinearBreadcrumb.Crumb crumb = breadCrumbs.getCrumb(breadCrumbs.size() - 2);
            breadCrumbs.setActive(crumb);

            breadCrumbs.removeCrumbAt(breadCrumbs.size() - 1);

            getRecyclerViewAdapter().clearEntites();
            getRecyclerViewAdapter().notifyDataSetChanged();

            this.mSha = crumb.getmAttachMsg();
            fileTreePresent.loadFileTree(mOwner, mRepoName, mSha, getRequestTag(), mUiCallBack);
        }
    }


    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClick(View view, int position) {
        FileTreeItem treeItem =getRecyclerViewAdapter().getItem(position);
        if (treeItem.getType().equals(FileTreeItem.MODE_TREE)) {
            intoItem(treeItem);
        } else if (treeItem.getType().equals(FileTreeItem.MODE_BLOB)) {
                String path = getAbosolutePath();
                String filePath = TextUtils.isEmpty(path) ? treeItem.getPath() :  path + "/" + treeItem.getPath();
                String url = String.format(API.GITHUB_FILE, mOwner, mRepoName, treeItem.getType(), mBran, filePath);
                Intent intent = WebActivity.newIntent(this, treeItem.getPath(), url);
                startActivity(intent);
        }
    }

    private void initTreeDataCallback() {
        mUiCallBack = new DefaultUiCallBack<Tree>(this) {
            @Override
            public void onSuccess(Tree tree) {
                onFirstLoadSuccess(tree.getTree());
            }

            @Override
            public void onLoading() {
                onLoadingOfLoadType(LOAD_FIRST);
            }


            @Override
            public void onDefaultError(VolleyError error) {
                onFirstLoadError(error);
            }
        };
    }
}
