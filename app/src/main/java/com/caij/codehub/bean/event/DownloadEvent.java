package com.caij.codehub.bean.event;

import com.caij.codehub.bean.Forkee;

/**
 * Created by Caij on 2015/10/30.
 */
public class DownloadEvent extends BaseEvent{

    private Forkee forkee;

    public Forkee getForkee() {
        return forkee;
    }

    public void setForkee(Forkee forkee) {
        this.forkee = forkee;
    }
}
