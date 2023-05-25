package com.tibame.timetotravel.service;

import com.tibame.timetotravel.view.ViewCompanyRoom;

import java.util.List;

public interface Room2Service {
    public List<ViewCompanyRoom> findAvailableCompany(String keyWord, Integer people, String start, String end);
}
