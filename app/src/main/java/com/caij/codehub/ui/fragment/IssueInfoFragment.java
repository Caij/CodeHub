package com.caij.codehub.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Comment;
import com.caij.codehub.present.CommentsPresent;
import com.caij.codehub.ui.activity.CommentActivity;
import com.caij.codehub.ui.activity.UserInfoActivity;
import com.caij.codehub.ui.adapter.AvatarOnClickListener;
import com.caij.codehub.ui.adapter.BaseAdapter;
import com.caij.codehub.ui.adapter.CommentAdapter;
import com.caij.codehub.widgets.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/18.
 */
public class IssueInfoFragment extends SwipeRefreshRecyclerViewFragment<Comment> implements AvatarOnClickListener {

    private String mIssueTitle;
    private String mIssueBody;
    private String mRepo;
    private String mIssueNumber;
    private String mOwner;
    private CommentsPresent mCommentsPresent;

    public static IssueInfoFragment newInstance(String owner, String repo, String issueNumber, String issueTitle, String issueBody) {
        IssueInfoFragment fragment = new IssueInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.REPO_NAME, repo);
        bundle.putString(Constant.USER_NAME, owner);
        bundle.putString(Constant.ISSUE_NUMBER, issueNumber);
        bundle.putString(Constant.ISSUE_TITLE, issueTitle);
        bundle.putString(Constant.ISSUE_BODY, issueBody);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        mIssueTitle = bundle.getString(Constant.ISSUE_TITLE);
        mIssueBody = bundle.getString(Constant.ISSUE_BODY);
        mRepo = bundle.getString(Constant.REPO_NAME);
        mIssueNumber = bundle.getString(Constant.ISSUE_NUMBER);
        mOwner = bundle.getString(Constant.USER_NAME);

        View issueContentHeadView = getActivity().getLayoutInflater().inflate(R.layout.item_issue_head, getLoadMoreRecyclerView(), false);
        TextView tvIssueTitle = (TextView) issueContentHeadView.findViewById(R.id.tv_issue_title);
        TextView tvIssueBody = (TextView) issueContentHeadView.findViewById(R.id.tv_issue_body);
        tvIssueTitle.setText(mIssueTitle);
        tvIssueBody.setText(mIssueBody);
        HeaderAndFooterRecyclerViewAdapter adapter = (HeaderAndFooterRecyclerViewAdapter) getLoadMoreRecyclerView().getAdapter();
        adapter.addHeaderView(issueContentHeadView);
        getLoadMoreRecyclerView().setLoadMoreEnable(false);

        getLoadMoreRecyclerView().addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity()).color(getResources().getColor(R.color.divider))
                        .size(getResources().getDimensionPixelSize(R.dimen.divider))
                        .margin(getResources().getDimensionPixelSize(R.dimen.small_margin)).build());


        mCommentsPresent = new CommentsPresent(this);
    }


    @Override
    protected int getAttachLayoutId() {
        return R.layout.fragment_issue_info;
    }

    @Override
    protected BaseAdapter<Comment> createRecyclerViewAdapter() {
        CommentAdapter adapter = new CommentAdapter(getActivity());
        adapter.setAvatarOnClickListener(this);
        return adapter;
    }

    @Override
    protected LoadMoreRecyclerView.LayoutManager createRecyclerViewLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void showEmptyView(boolean isVisible) {

    }

    @Override
    protected void onUserFirstVisible() {
        mCommentsPresent.getIssuesComments(mOwner, mRepo, mIssueNumber);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(View view, int position) {

    }


    @Override
    public void onAvatarClick(View view, int position) {
        Comment comment = getRecyclerViewAdapter().getItem(position);
        Intent intent = UserInfoActivity.newIntent(getActivity(), comment.getUser().getLogin());
        startActivity(intent);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mCommentsPresent.getIssuesComments(mOwner, mRepo, mIssueNumber);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_issue, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.comment) {
            Intent intent = CommentActivity.newIntent(getActivity(), mOwner, mRepo, mIssueNumber);
            startActivityForResult(intent, Constant.ISSUE_COMMENT_REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constant.ISSUE_COMMENT_REQUEST_CODE) {
                Comment comment = (Comment) data.getSerializableExtra(Constant.COMMENT);
                getRecyclerViewAdapter().addEntity(comment);
                getRecyclerViewAdapter().notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onDestroyView() {
        mCommentsPresent.onDeath();
        super.onDestroyView();
    }

}
