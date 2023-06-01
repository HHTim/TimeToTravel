package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.C2AMessage;

import java.util.List;

public interface C2AMessageService extends C2AMessageViewService{
    void insert(C2AMessage c2AMessage);
    List<C2AMessage> findByPage(Integer currPage, Integer rows);
    PageBean<C2AMessage> findMsgByPageRowData(Integer currPage, Integer rows);
    List<C2AMessage> getAll();
}
