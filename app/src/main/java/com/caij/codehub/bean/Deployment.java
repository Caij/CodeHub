package com.caij.codehub.bean;

/**
 * Created by Caij on 2015/10/30.
 */
public class Deployment {


    /**
     * url : https://api.github.com/repos/baxterthehacker/public-repo/deployments/710692
     * id : 710692
     * sha : 9049f1265b7d61be4a8904a9a27120d2064dab3b
     * ref : master
     * task : deploy
     * payload : {}
     * environment : production
     * description : null
     * creator : {"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false}
     * created_at : 2015-05-05T23:40:38Z
     * updated_at : 2015-05-05T23:40:38Z
     * statuses_url : https://api.github.com/repos/baxterthehacker/public-repo/deployments/710692/statuses
     * repository_url : https://api.github.com/repos/baxterthehacker/public-repo
     */

    private String url;
    private int id;
    private String sha;
    private String ref;
    private String task;
    private String environment;
    private Object description;
    private String created_at;
    private String updated_at;
    private String statuses_url;
    private String repository_url;
    private String payload;
    private User creator;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setStatuses_url(String statuses_url) {
        this.statuses_url = statuses_url;
    }

    public void setRepository_url(String repository_url) {
        this.repository_url = repository_url;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public String getSha() {
        return sha;
    }

    public String getRef() {
        return ref;
    }

    public String getTask() {
        return task;
    }

    public String getEnvironment() {
        return environment;
    }

    public Object getDescription() {
        return description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getStatuses_url() {
        return statuses_url;
    }

    public String getRepository_url() {
        return repository_url;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
