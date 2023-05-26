package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.view.A2UMsgView;

import java.util.List;

public interface A2UMessageViewService {
    List<A2UMsgView> findViewByPage(Integer currPage, Integer row);
    PageBean<A2UMsgView> findViewByPageRowData(Integer currPage, Integer rows);
    List<A2UMsgView> getViewAll();
}
