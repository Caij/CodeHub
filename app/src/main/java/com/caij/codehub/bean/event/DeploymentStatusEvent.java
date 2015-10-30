package com.caij.codehub.bean.event;

import com.caij.codehub.bean.Deployment;
import com.caij.codehub.bean.DeploymentStatus;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.bean.User;

/**
 * Created by Caij on 2015/10/30.
 */
public class DeploymentStatusEvent extends BaseEvent{

    private DeploymentStatus deployment_status;
    private Deployment deployment;

    public void setDeployment_status(DeploymentStatus deployment_status) {
        this.deployment_status = deployment_status;
    }

    public DeploymentStatus getDeployment_status() {
        return deployment_status;
    }

    public Deployment getDeployment() {
        return deployment;
    }

    public void setDeployment(Deployment deployment) {
        this.deployment = deployment;
    }
}
