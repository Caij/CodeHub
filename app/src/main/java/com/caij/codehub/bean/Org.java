package com.caij.codehub.bean;

/**
 * Created by Caij on 2015/10/30.
 */
public class Org {

    private int id;
    private String login;
    private String gravatar_id;
    private String url;
    private String avatar_url;

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

}
