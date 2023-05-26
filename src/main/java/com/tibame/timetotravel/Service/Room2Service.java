package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.SearchRoom;

import java.util.List;

public interface Room2Service {
    public List<SearchRoom> findAvailableCompany(String keyWord, Integer people, String start, String end);
}
