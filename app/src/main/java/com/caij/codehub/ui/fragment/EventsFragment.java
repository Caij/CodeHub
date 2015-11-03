package com.caij.codehub.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.VolleyError;
import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.bean.Page;
import com.caij.codehub.bean.event.Event;
import com.caij.codehub.bean.event.EventWrap;
import com.caij.codehub.bean.event.IssueCommentEvent;
import com.caij.codehub.bean.event.IssuesEvent;
import com.caij.codehub.presenter.Present;
import com.caij.codehub.presenter.EventsPresenter;
import com.caij.codehub.presenter.PresenterFactory;
import com.caij.codehub.ui.activity.IssueActivity;
import com.caij.codehub.ui.activity.RepositoryInfoActivity;
import com.caij.codehub.ui.adapter.EventsAdapter;
import com.caij.codehub.ui.listener.EventsUi;
import com.caij.lib.utils.SPUtils;
import com.caij.lib.utils.ToastUtil;

import java.util.List;


/**
 * Created by Caij on 2015/9/24.
 */
public class EventsFragment extends ListFragment<EventsAdapter, EventWrap> implements EventsUi {

    Page mPage;
    private EventsPresenter mEventsPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(false);
        mListView.setDivider(null);
        mPage = new Page();
        mEventsPresenter = PresenterFactory.newPresentInstance(EventsPresenter.class, EventsUi.class, this);
    }

    @Override
    protected EventsAdapter createAdapter() {
        return new EventsAdapter(getActivity(), null);
    }

    @Override
    protected void onUserFirstVisible() {
        mEventsPresenter.getReceivedEvents(SPUtils.get(Constant.USER_NAME, ""), SPUtils.get(Constant.USER_TOKEN, ""),
                Present.LoadType.FIRSTLOAD, mPage);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        EventWrap event = (EventWrap) adapterView.getAdapter().getItem(i);
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

    @Override
    public void onRefresh() {
        mPage.reset();
        mEventsPresenter.getReceivedEvents(SPUtils.get(Constant.USER_NAME, ""), SPUtils.get(Constant.USER_TOKEN, ""),
                Present.LoadType.REFRESH, mPage);
    }

    @Override
    public void onLoadMore() {
        mEventsPresenter.getReceivedEvents(SPUtils.get(Constant.USER_NAME, ""), SPUtils.get(Constant.USER_TOKEN, ""),
                Present.LoadType.LOADMOER, mPage);
    }

    @Override
    public void onFirstLoadSuccess(List<EventWrap> entities) {
        super.onFirstLoadSuccess(entities);
        mPage.next();
        mListView.setNoMore(entities.size() < mPage.getPageDataCount());
    }

    @Override
    public void onRefreshSuccess(List<EventWrap> entities) {
        super.onRefreshSuccess(entities);

        mPage.next();
        mListView.setNoMore(entities.size() < mPage.getPageDataCount());
    }

    @Override
    public void onReFreshBtnClick(View view) {
        super.onReFreshBtnClick(view);
        mPage.reset();
        mEventsPresenter.getReceivedEvents(SPUtils.get(Constant.USER_NAME, ""), SPUtils.get(Constant.USER_TOKEN, ""),
                Present.LoadType.FIRSTLOAD, mPage);
    }

    @Override
    public void onRefreshError(VolleyError error) {
        super.onRefreshError(error);
        mPage.scrollBack(); //用于刷新的时候重置page刷新错误，导致下拉index出错。
    }
}
