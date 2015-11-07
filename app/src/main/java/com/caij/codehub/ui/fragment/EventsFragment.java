package com.caij.codehub.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.caij.codehub.CodeHubApplication;
import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.event.Event;
import com.caij.codehub.bean.event.EventWrap;
import com.caij.codehub.bean.event.IssueCommentEvent;
import com.caij.codehub.bean.event.IssuesEvent;
import com.caij.codehub.presenter.EventsPresenter;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.ui.activity.IssueActivity;
import com.caij.codehub.ui.activity.RepositoryInfoActivity;
import com.caij.codehub.ui.adapter.BaseAdapter;
import com.caij.codehub.ui.adapter.EventsAdapter;
import com.caij.codehub.ui.intf.EventsUi;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;
import com.caij.lib.utils.SPUtils;
import com.caij.lib.utils.ToastUtil;


import java.util.List;


/**
 * Created by Caij on 2015/9/24.
 */
public class EventsFragment extends SwipeRefreshRecyclerViewFragment<EventWrap> implements EventsUi {

    Page mPage;
    private EventsPresenter mEventsPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(false);
        mPage = new Page();
        mEventsPresenter = PresenterFactory.newPresentInstance(EventsPresenter.class);
    }

    @Override
    protected void onUserFirstVisible() {
        mEventsPresenter.getReceivedEvents(SPUtils.getString(Constant.USER_NAME, ""), SPUtils.getString(Constant.USER_TOKEN, ""),
                mPage, this, mFirstLoadUiCallBack);
    }

    @Override
    public void onRefresh() {
        mEventsPresenter.getReceivedEvents(SPUtils.getString(Constant.USER_NAME, ""), SPUtils.getString(Constant.USER_TOKEN, ""),
                mPage.createRefreshPage(), this, mLoadRefreshUiCallBack);
    }

    @Override
    public void onFirstLoadSuccess(List<EventWrap> entities) {
        super.onFirstLoadSuccess(entities);
        mPage.next();
        getLoadMoreRecyclerView().setState(entities.size() < mPage.getPageDataCount() ? LoadMoreRecyclerView.STATE_NO_MORE : LoadMoreRecyclerView.STATE_NORMAL);
    }

    @Override
    public void onRefreshSuccess(List<EventWrap> entities) {
        super.onRefreshSuccess(entities);
        mPage.reset();
        mPage.next();
        getLoadMoreRecyclerView().setState(entities.size() < mPage.getPageDataCount() ? LoadMoreRecyclerView.STATE_NO_MORE : LoadMoreRecyclerView.STATE_NORMAL);
    }

    @Override
    public void onLoadMoreSuccess(List<EventWrap> entities) {
        super.onLoadMoreSuccess(entities);
        mPage.next();
        getLoadMoreRecyclerView().setState(entities.size() < mPage.getPageDataCount() ? LoadMoreRecyclerView.STATE_NO_MORE : LoadMoreRecyclerView.STATE_NORMAL);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mPage.reset();
        mEventsPresenter.getReceivedEvents(CodeHubPrefs.get().getUsername(), getToken(),
                mPage, this, mFirstLoadUiCallBack);
    }

    @Override
    protected BaseAdapter<EventWrap> createRecyclerViewAdapter() {
        return new EventsAdapter(getActivity(), null);
    }

    @Override
    protected LoadMoreRecyclerView.LayoutManager createRecyclerViewLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void onLoadMore() {
        mEventsPresenter.getReceivedEvents(CodeHubPrefs.get().getUsername(), getToken(),
                mPage, this, mLoadMoreUiCallBack);
    }

    @Override
    public void onItemClick(View view, int position) {
        EventWrap event = getRecyclerViewAdapter().getItem(position);
        if (Event.COMMIT_COMMENT.equals(event.getType())) {
        }else if (Event.CREATE.equals(event.getType())) {
        }else if (Event.DELETE.equals(event.getType())) {
        }else if (Event.DEPLOYMENT.equals(event.getType())) {
        }else if (Event.DEPLOYMENT_STATUS.equals(event.getType())) {
        }else if (Event.ISSUE_COMMENT.equals(event.getType())) {
            IssueCommentEvent realEvent = (IssueCommentEvent) event.getRealEvent();
            String[] repoInfo =  event.getRepo().getName().split("/");
            Intent intent = IssueActivity.newIntent(getActivity(), repoInfo[0], repoInfo[1],
                    String.valueOf(realEvent.getIssue().getNumber()), realEvent.getIssue().getTitle(), realEvent.getIssue().getBody());
            startActivity(intent);
        }else if (Event.ISSUES.equals(event.getType())) {
            IssuesEvent realEvent = (IssuesEvent) event.getRealEvent();
            String[] repoInfo =  event.getRepo().getName().split("/");
            Intent intent = IssueActivity.newIntent(getActivity(), repoInfo[0], repoInfo[1],
                    String.valueOf(realEvent.getIssue().getNumber()), realEvent.getIssue().getTitle(), realEvent.getIssue().getBody());
            startActivity(intent);
        }else if (Event.MEMBER.equals(event.getType())) {
        }else if (Event.MEMBERSHIP.equals(event.getType())) {
        }else if (Event.PULL_REQUEST.equals(event.getType())) {
            try {
                String[] repoInfo =  event.getRepo().getName().split("/");
                Intent intent = RepositoryInfoActivity.newInstance(getActivity(), repoInfo[0], repoInfo[1]);
                startActivity(intent);
            }catch (Exception e) {
                ToastUtil.show(getActivity(), R.string.data_analysis_error);
            }
        }else if (Event.PULL_REQUEST_REVIEW_COMMENT.equals(event.getType())) {
        }else if (Event.PUSH.equals(event.getType())) {
            try {
                String[] repoInfo =  event.getRepo().getName().split("/");
                Intent intent = RepositoryInfoActivity.newInstance(getActivity(), repoInfo[0], repoInfo[1]);
                startActivity(intent);
            }catch (Exception e) {
                ToastUtil.show(getActivity(), R.string.data_analysis_error);
            }
        }else if (Event.REPOSITORY.equals(event.getType())) {
        }else if (Event.TEAM_ADD.equals(event.getType())) {
        }else if (Event.WATCH.equals(event.getType())) {
        }
    }
}
