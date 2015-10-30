package com.caij.codehub.bean.event;

import com.caij.codehub.bean.Entity;
import com.caij.codehub.bean.Repository;
import com.caij.codehub.bean.User;

/**
 * Created by Caij on 2015/10/30.
 */
public class BaseEvent extends Entity{

    private User sender;
    private Repository repository;

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
