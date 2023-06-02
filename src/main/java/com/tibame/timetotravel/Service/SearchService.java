package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.SearchRoomDto;

import java.lang.reflect.InvocationTargetException;

public interface SearchService {
    public PageBean<SearchRoomDto> findAvailableCompany(String keyWord, Integer people, String start, String end, Integer currPage) throws InvocationTargetException, IllegalAccessException;
}
