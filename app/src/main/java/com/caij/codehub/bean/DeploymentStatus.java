package com.caij.codehub.bean;

/**
 * Created by Caij on 2015/10/30.
 */
public class DeploymentStatus {

    /**
     * url : https://api.github.com/repos/baxterthehacker/public-repo/deployments/710692/statuses/1115122
     * id : 1115122
     * state : success
     * creator : {"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false}
     * description : null
     * target_url : null
     * created_at : 2015-05-05T23:40:39Z
     * updated_at : 2015-05-05T23:40:39Z
     * deployment_url : https://api.github.com/repos/baxterthehacker/public-repo/deployments/710692
     * repository_url : https://api.github.com/repos/baxterthehacker/public-repo
     */

    private String url;
    private int id;
    private String state;
    private Object description;
    private Object target_url;
    private String created_at;
    private String updated_at;
    private String deployment_url;
    private String repository_url;
    private User creator;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public void setTarget_url(Object target_url) {
        this.target_url = target_url;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setDeployment_url(String deployment_url) {
        this.deployment_url = deployment_url;
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

    public String getState() {
        return state;
    }

    public Object getDescription() {
        return description;
    }

    public Object getTarget_url() {
        return target_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getDeployment_url() {
        return deployment_url;
    }

    public String getRepository_url() {
        return repository_url;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
