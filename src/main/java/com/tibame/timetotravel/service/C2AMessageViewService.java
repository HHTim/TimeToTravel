package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.view.C2AMsgView;

import java.util.List;

public interface C2AMessageViewService {
    List<C2AMsgView> findViewByPage(Integer currPage, Integer rows);
    PageBean<C2AMsgView> findViewByPageRowData(Integer currPage, Integer rows);
    List<C2AMsgView> getViewAll();
    Integer findViewByKeyWords(String keyword);
    List<C2AMsgView>findViewByKeyWordsPage(String keyword, Integer currPage, Integer rows);
    PageBean<C2AMsgView>findBeanPageViewByKeyWords(String keyword, Integer currPage, Integer rows);
    Integer findViewByDateRange(String startDate, String endDate);
    List<C2AMsgView>findViewByDateRangePage(String startDate, String endDate, Integer currPage, Integer rows);
    PageBean<C2AMsgView> findBeanPageViewByDateRange(String startDate, String endDate, Integer currPage, Integer rows);

    List<C2AMsgView> getNotifyMsgByComps(Integer startIndex, Integer endIndex);
}
