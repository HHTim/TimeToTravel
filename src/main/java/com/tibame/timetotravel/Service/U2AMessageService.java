package com.tibame.timetotravel.service;

import com.tibame.timetotravel.entity.U2AMessage;

import java.util.List;

public interface U2AMessageService {

    void insert(U2AMessage u2AMessage);

    List<U2AMessage> getALl();
}
