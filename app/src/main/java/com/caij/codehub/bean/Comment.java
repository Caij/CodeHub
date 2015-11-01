package com.caij.codehub.bean;

import java.util.Date;

/**
 * Created by Caij on 2015/9/24.
 */
public class Comment extends Entity{

    /**
     * url : https://api.github.com/repos/android-cn/android-discuss/issues/comments/142839468
     * html_url : https://github.com/android-cn/android-discuss/issues/264#issuecomment-142839468
     * issue_url : https://api.github.com/repos/android-cn/android-discuss/issues/264
     * id : 142839468
     * user : {"login":"Shawlaw","id":8605553,"avatar_url":"https://avatars.githubusercontent.com/u/8605553?v=3","gravatar_id":"","url":"https://api.github.com/users/Shawlaw","html_url":"https://github.com/Shawlaw","followers_url":"https://api.github.com/users/Shawlaw/followers","following_url":"https://api.github.com/users/Shawlaw/following{/other_user}","gists_url":"https://api.github.com/users/Shawlaw/gists{/gist_id}","starred_url":"https://api.github.com/users/Shawlaw/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/Shawlaw/subscriptions","organizations_url":"https://api.github.com/users/Shawlaw/orgs","repos_url":"https://api.github.com/users/Shawlaw/repos","events_url":"https://api.github.com/users/Shawlaw/events{/privacy}","received_events_url":"https://api.github.com/users/Shawlaw/received_events","type":"User","site_admin":false}
     * created_at : 2015-09-24T07:35:52Z
     * updated_at : 2015-09-24T07:35:52Z
     */

    private String url;
    private String html_url;
    private String issue_url;
    private int id;
    private User user;
    private Date created_at;
    private Date updated_at;
    private String body;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public void setIssue_url(String issue_url) {
        this.issue_url = issue_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getUrl() {
        return url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getIssue_url() {
        return issue_url;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
