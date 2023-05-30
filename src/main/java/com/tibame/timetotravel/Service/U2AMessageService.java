package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.U2AMessage;

import java.util.List;

public interface U2AMessageService extends U2AMessageViewService{
    void insert(U2AMessage u2AMessage);
    List<U2AMessage> findByPage(Integer currPage, Integer rows);
    PageBean<U2AMessage> findMsgByPageRowData(Integer currPage, Integer rows);
    List<U2AMessage> getAll();
}
