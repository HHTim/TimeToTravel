package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.view.A2CMsgView;

import java.util.List;

public interface A2CMessageViewService {
    List<A2CMsgView> findViewByPage(Integer currPage, Integer row);
    PageBean<A2CMsgView> findViewByPageRowData(Integer currPage, Integer rows);
    List<A2CMsgView> getViewAll();
}
