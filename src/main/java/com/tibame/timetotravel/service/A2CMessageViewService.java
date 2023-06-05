package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.view.A2CMsgView;

import java.util.List;

public interface A2CMessageViewService {
    List<A2CMsgView> findViewByPage(Integer currPage, Integer rows);
    PageBean<A2CMsgView> findViewByPageRowData(Integer currPage, Integer rows);
    List<A2CMsgView> getViewAll();

    Integer findViewByDateRange(String startDate, String endDate);
    List<A2CMsgView>findViewByDateRangePage(String startDate, String endDate, Integer currPage, Integer rows);
    PageBean<A2CMsgView> findBeanPageViewByDateRange(String startDate, String endDate, Integer currPage, Integer rows);

    List<A2CMsgView> getNotifyMsgById(Integer comId, Integer startIndex, Integer endIndex);
}
