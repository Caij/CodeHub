package com.caij.codehub.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Caij on 2015/9/21.
 */
public class SearchRepository {

    /**
     */

    private int total_count;
    private boolean incomplete_results;
    private List<Repository> items;

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public void setItems(List<Repository> items) {
        this.items = items;
    }

    public int getTotal_count() {
        return total_count;
    }

    public boolean getIncomplete_results() {
        return incomplete_results;
    }

    public List<Repository> getItems() {
        return items;
    }

}
