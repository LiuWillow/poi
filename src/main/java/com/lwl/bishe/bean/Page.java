package com.lwl.bishe.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * date  2018/4/3
 * author liuwillow
 **/
public class Page<T> {
    private List<T> list;
    private int currentPage;
    private int maxPage;
    private int currentCount;
    private static final int DEFAULT_COUNT_PER_PAGE = 10;

    public Page(){}

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }
}
