package com.tibame.timetotravel.common;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageBean<T> {

    private int pageSize;
    private List<T> rows;

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public List<T> getRows() {
        return rows;
    }
    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
