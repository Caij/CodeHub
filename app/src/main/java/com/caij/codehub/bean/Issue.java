package com.caij.codehub.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Caij on 2015/9/24.
 */
public class Issue extends Entity implements Serializable{


    /**
     * url : https://api.github.com/repos/baxterthehacker/public-repo/issues/2
     * labels_url : https://api.github.com/repos/baxterthehacker/public-repo/issues/2/labels{/name}
     * comments_url : https://api.github.com/repos/baxterthehacker/public-repo/issues/2/comments
     * events_url : https://api.github.com/repos/baxterthehacker/public-repo/issues/2/events
     * html_url : https://github.com/baxterthehacker/public-repo/issues/2
     * id : 73464126
     * number : 2
     * title : Spelling error in the README file
     * user : {"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false}
     * labels : [{"url":"https://api.github.com/repos/baxterthehacker/public-repo/labels/bug","name":"bug","color":"fc2929"}]
     * state : open
     * locked : false
     * assignee : null
     * milestone : null
     * comments : 1
     * created_at : 2015-05-05T23:40:28Z
     * updated_at : 2015-05-05T23:40:28Z
     * closed_at : null
     * body : It looks like you accidently spelled 'commit' with two 't's.
     */

    private String url;
    private String labels_url;
    private String comments_url;
    private String events_url;
    private String html_url;
    private int id;
    private int number;
    private String title;
    private String state;
    private boolean locked;
    private Object assignee;
    private Object milestone;
    private int comments;
    private Date created_at;
    private Date updated_at;
    private Date closed_at;
    private String body;
    private List<LabelsEntity> labels;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLabels_url(String labels_url) {
        this.labels_url = labels_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setAssignee(Object assignee) {
        this.assignee = assignee;
    }

    public void setMilestone(Object milestone) {
        this.milestone = milestone;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setClosed_at(Date closed_at) {
        this.closed_at = closed_at;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setLabels(List<LabelsEntity> labels) {
        this.labels = labels;
    }

    public String getUrl() {
        return url;
    }

    public String getLabels_url() {
        return labels_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getState() {
        return state;
    }

    public boolean getLocked() {
        return locked;
    }

    public Object getAssignee() {
        return assignee;
    }

    public Object getMilestone() {
        return milestone;
    }

    public int getComments() {
        return comments;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public Date getClosed_at() {
        return closed_at;
    }

    public String getBody() {
        return body;
    }

    public List<LabelsEntity> getLabels() {
        return labels;
    }

    public static class LabelsEntity {
        /**
         * url : https://api.github.com/repos/baxterthehacker/public-repo/labels/bug
         * name : bug
         * color : fc2929
         */

        private String url;
        private String name;
        private String color;

        public void setUrl(String url) {
            this.url = url;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }

        public String getColor() {
            return color;
        }
    }
}
