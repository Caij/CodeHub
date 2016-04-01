package com.caij.codehub.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caij on 2016/4/1.
 */
public class AuthorizationParameters {

    private List<String> scopes = new ArrayList<>();
    private String note;

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
