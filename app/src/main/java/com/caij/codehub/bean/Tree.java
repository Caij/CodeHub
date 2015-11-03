package com.caij.codehub.bean;

import java.util.List;

/**
 * Created by Caij
 */
public class Tree {
    private static final long serialVersionUID = -6181332657279059683L;

    private List<FileTreeItem> tree;
    private String sha;
    private String url;

    public Tree() {

    }


    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public List<FileTreeItem> getTree() {
        return tree;
    }

    public void setTree(List<FileTreeItem> tree) {
        this.tree = tree;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "Tree{" +
                "sha='" + sha + '\'' +
                ", tree=" + tree +
                ", url='" + url + '\'' +
                '}';
    }
}