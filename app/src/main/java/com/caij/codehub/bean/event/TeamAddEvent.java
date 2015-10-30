package com.caij.codehub.bean.event;

import com.caij.codehub.bean.Org;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.bean.Team;

/**
 * Created by Caij on 2015/10/30.
 */
public class TeamAddEvent extends BaseEvent{
    private Team team;
    private Org organization;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Org getOrganization() {
        return organization;
    }

    public void setOrganization(Org organization) {
        this.organization = organization;
    }
}
