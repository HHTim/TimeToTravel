package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.A2CMessage;

import java.util.List;

public interface A2CMessageService extends A2CMessageViewService{
    void insert(A2CMessage a2UMessage);
    List<A2CMessage> findByPage(Integer currPage, Integer rows);
    PageBean<A2CMessage> findAllByPageRowData(Integer currPage, Integer rows);
    List<A2CMessage> getAll();
}
