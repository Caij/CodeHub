package com.caij.codehub.bean.event;

import com.caij.codehub.bean.Org;
import com.caij.codehub.bean.Team;
import com.caij.codehub.bean.User;

/**
 * Created by Caij on 2015/10/30.
 */
public class MembershipEvent extends BaseEvent{

    /**
     * action : added
     * scope : team
     * member : {"login":"kdaigle","id":2501,"avatar_url":"https://avatars.githubusercontent.com/u/2501?v=3","gravatar_id":"","url":"https://api.github.com/users/kdaigle","html_url":"https://github.com/kdaigle","followers_url":"https://api.github.com/users/kdaigle/followers","following_url":"https://api.github.com/users/kdaigle/following{/other_user}","gists_url":"https://api.github.com/users/kdaigle/gists{/gist_id}","starred_url":"https://api.github.com/users/kdaigle/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/kdaigle/subscriptions","organizations_url":"https://api.github.com/users/kdaigle/orgs","repos_url":"https://api.github.com/users/kdaigle/repos","events_url":"https://api.github.com/users/kdaigle/events{/privacy}","received_events_url":"https://api.github.com/users/kdaigle/received_events","type":"User","site_admin":true}
     * team : {"name":"Contractors","id":123456,"slug":"contractors","permission":"admin","url":"https://api.github.com/teams/123456","members_url":"https://api.github.com/teams/123456/members{/member}","repositories_url":"https://api.github.com/teams/123456/repos"}
     * organization : {"login":"baxterandthehackers","id":7649605,"url":"https://api.github.com/orgs/baxterandthehackers","repos_url":"https://api.github.com/orgs/baxterandthehackers/repos","events_url":"https://api.github.com/orgs/baxterandthehackers/events","members_url":"https://api.github.com/orgs/baxterandthehackers/members{/member}","public_members_url":"https://api.github.com/orgs/baxterandthehackers/public_members{/member}","avatar_url":"https://avatars.githubusercontent.com/u/7649605?v=2"}
     */

    private String action;
    private String scope;
    private User member;
    private Org organization;
    private Team team;

    public void setAction(String action) {
        this.action = action;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAction() {
        return action;
    }

    public String getScope() {
        return scope;
    }

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public Org getOrganization() {
        return organization;
    }

    public void setOrganization(Org organization) {
        this.organization = organization;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
