package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.view.A2UMsgView;

import java.util.List;

public interface A2UMessageViewService {
    List<A2UMsgView> findViewByPage(Integer currPage, Integer row);
    PageBean<A2UMsgView> findViewByPageRowData(Integer currPage, Integer rows);
    List<A2UMsgView> getViewAll();
    Integer findViewByDateRange(String startDate, String endDate);
    List<A2UMsgView>findViewByDateRangePage(String startDate, String endDate, Integer currPage, Integer rows);
    PageBean<A2UMsgView> findBeanPageViewByDateRange(String startDate, String endDate, Integer currPage, Integer rows);

    List<A2UMsgView> getNotifyMsgById(Integer userId, Integer startIndex, Integer endIndex);
}
