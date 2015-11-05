package com.caij.codehub.bean;

/**
 * Created by Caij on 2015/9/21.
 */
public class Page {

    public final static int DEFAULT_PAGE_INDEX = 1;

    public final static int DEFAULT_PAGE_DATA_COUNT = 20;

    private int pageIndex;
    private final int pageDataCount;

    public Page() {
        this(DEFAULT_PAGE_INDEX, DEFAULT_PAGE_DATA_COUNT);
    }

    public Page(int pageIndex, int pageDataCount) {
        this.pageDataCount = pageDataCount;
        this.pageIndex = pageIndex;
    }

    public Page(int pageDataCount) {
        this(DEFAULT_PAGE_INDEX, pageDataCount);
    }

    public void next() {
        pageIndex ++;
    }

    public void reset() {
        pageIndex = 1;
    }


    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageDataCount() {
        return pageDataCount;
    }

    public Page createRefreshPage() {
        return new Page(pageDataCount);
    }
}
