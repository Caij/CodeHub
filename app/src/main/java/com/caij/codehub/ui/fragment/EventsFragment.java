package com.caij.codehub.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.caij.codehub.CodeHubPrefs;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.event.Event;
import com.caij.codehub.bean.event.EventWrap;
import com.caij.codehub.bean.event.IssueCommentEvent;
import com.caij.codehub.bean.event.IssuesEvent;
import com.caij.codehub.interactor.EventsInteractor;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.present.EventsPresent;
import com.caij.codehub.present.LoadType;
import com.caij.codehub.ui.activity.IssueInfoActivity;
import com.caij.codehub.ui.activity.RepositoryInfoActivity;
import com.caij.codehub.ui.activity.UserInfoActivity;
import com.caij.codehub.ui.adapter.AvatarOnClickListener;
import com.caij.codehub.ui.adapter.BaseAdapter;
import com.caij.codehub.ui.adapter.EventsAdapter;
import com.caij.codehub.widgets.recyclerview.LoadMoreRecyclerView;
import com.caij.lib.utils.ToastUtil;


import java.util.List;


/**
 * Created by Caij on 2015/9/24.
 */
public class EventsFragment extends SwipeRefreshRecyclerViewFragment<Event> implements AvatarOnClickListener {

    Page mPage;
    private String mToken;
    private EventsPresent mEventsPresent;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(false);
        mPage = new Page();
        mEventsPresent = new EventsPresent(this);
        mToken = CodeHubPrefs.get().getToken();
    }

    @Override
    protected void onUserFirstVisible() {
        mEventsPresent.getReceivedEvents(LoadType.FIRST, CodeHubPrefs.get().getUsername(), mToken, mPage);
    }

    @Override
    public void onRefresh() {
        mEventsPresent.getReceivedEvents(LoadType.REFRESH, CodeHubPrefs.get().getUsername(), mToken, mPage.createRefreshPage());
    }

    @Override
    public void onFirstLoadSuccess(List<Event> entities) {
        super.onFirstLoadSuccess(entities);
        mPage.next();
        getLoadMoreRecyclerView().setState(entities.size() < mPage.getPageDataCount() ? LoadMoreRecyclerView.STATE_NO_MORE : LoadMoreRecyclerView.STATE_NORMAL);
    }

    @Override
    public void onRefreshSuccess(List<Event> entities) {
        super.onRefreshSuccess(entities);
        mPage.reset();
        mPage.next();
        getLoadMoreRecyclerView().setState(entities.size() < mPage.getPageDataCount() ? LoadMoreRecyclerView.STATE_NO_MORE : LoadMoreRecyclerView.STATE_NORMAL);
    }

    @Override
    public void onLoadMoreSuccess(List<Event> entities) {
        super.onLoadMoreSuccess(entities);
        mPage.next();
        getLoadMoreRecyclerView().setState(entities.size() < mPage.getPageDataCount() ? LoadMoreRecyclerView.STATE_NO_MORE : LoadMoreRecyclerView.STATE_NORMAL);
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mPage.reset();
        mEventsPresent.getReceivedEvents(LoadType.FIRST, CodeHubPrefs.get().getUsername(), mToken, mPage);
    }

    @Override
    protected BaseAdapter<Event> createRecyclerViewAdapter() {
        EventsAdapter adapter = new EventsAdapter(getActivity());
        adapter.setAvatarOnClickListener(this);
        return adapter;
    }

    @Override
    public void onAvatarClick(View view, int position) {
        Event eventWrap = getRecyclerViewAdapter().getItem(position);
        Intent intent = UserInfoActivity.newIntent(getActivity(), eventWrap.getActor().getLogin());
        startActivity(intent);
    }

    @Override
    protected LoadMoreRecyclerView.LayoutManager createRecyclerViewLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void onLoadMore() {
        mEventsPresent.getReceivedEvents(LoadType.MORE, CodeHubPrefs.get().getUsername(), mToken, mPage);
    }

    @Override
    public void onItemClick(View view, int position) {
        try {
            Event event = getRecyclerViewAdapter().getItem(position);
            if (Event.COMMIT_COMMENT.equals(event.getType())) {
            }else if (Event.CREATE.equals(event.getType())) {
            }else if (Event.DELETE.equals(event.getType())) {
            }else if (Event.DEPLOYMENT.equals(event.getType())) {
            }else if (Event.DEPLOYMENT_STATUS.equals(event.getType())) {
            }else if (Event.ISSUE_COMMENT.equals(event.getType())) {
                IssueCommentEvent realEvent = (IssueCommentEvent) event.getRealEvent();
                String[] repoInfo =  event.getRepo().getName().split("/");
                Intent intent = IssueInfoActivity.newIntent(getActivity(), repoInfo[0], repoInfo[1],
                        String.valueOf(realEvent.getIssue().getNumber()), realEvent.getIssue().getTitle(), realEvent.getIssue().getBody());
                startActivity(intent);
            }else if (Event.ISSUES.equals(event.getType())) {
                IssuesEvent realEvent = (IssuesEvent) event.getRealEvent();
                String[] repoInfo =  event.getRepo().getName().split("/");
                Intent intent = IssueInfoActivity.newIntent(getActivity(), repoInfo[0], repoInfo[1],
                        String.valueOf(realEvent.getIssue().getNumber()), realEvent.getIssue().getTitle(), realEvent.getIssue().getBody());
                startActivity(intent);
            }else if (Event.MEMBER.equals(event.getType())) {
            }else if (Event.MEMBERSHIP.equals(event.getType())) {
            }else if (Event.PULL_REQUEST_REVIEW_COMMENT.equals(event.getType())) {
            }else if (Event.REPOSITORY.equals(event.getType())) {
            }else if (Event.TEAM_ADD.equals(event.getType())) {
            }else if (Event.WATCH.equals(event.getType()) || Event.FORK.equals(event.getType()) || Event.PULL_REQUEST.equals(event.getType())) {
                String[] repoInfo =  event.getRepo().getName().split("/");
                Intent intent = RepositoryInfoActivity.newInstance(getActivity(), repoInfo[0], repoInfo[1]);
                startActivity(intent);
            }
        } catch (Exception e) {
            ToastUtil.show(getActivity(), R.string.data_analysis_error);
        }
    }

    @Override
    public void onDestroyView() {
        mEventsPresent.onDeath();
        super.onDestroyView();
    }

}
