package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.view.U2AMsgView;

import java.util.List;

public interface U2AMessageViewService{
    List<U2AMsgView> findViewByPage(Integer currPage, Integer row);
    PageBean<U2AMsgView> findViewByPageRowData(Integer currPage, Integer rows);
    List<U2AMsgView> getViewAll();

    Integer findViewByKeyWords(String keyword);
    List<U2AMsgView>findViewByKeyWordsPage(String keyword, Integer currPage, Integer rows);
    PageBean<U2AMsgView>findBeanPageViewByKeyWords(String keyword, Integer currPage, Integer rows);
    Integer findViewByDateRange(String startDate, String endDate);
    List<U2AMsgView>findViewByDateRangePage(String startDate, String endDate, Integer currPage, Integer rows);
    PageBean<U2AMsgView> findBeanPageViewByDateRange(String startDate, String endDate, Integer currPage, Integer rows);

    List<U2AMsgView> getNotifyMsgByUsers(Integer startIndex, Integer endIndex);

}
