package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.A2UMessage;

import java.util.List;

public interface A2UMessageService {

    void insert(A2UMessage a2UMessage);

    List<A2UMessage> findByPage(Integer currPage, Integer row);

    PageBean<A2UMessage> findAnnPageByRowData(Integer currPage, Integer rows);
    List<A2UMessage> getALl();
}
