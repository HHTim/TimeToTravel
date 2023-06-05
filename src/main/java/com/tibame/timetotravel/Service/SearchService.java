package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.SearchRoomDto;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface SearchService {
    PageBean<SearchRoomDto> findAvailableCompany(String keyWord, Integer people, String start, String end, Integer currPage) throws InvocationTargetException, IllegalAccessException;

    List<SearchRoomDto> findNearSceneRooms(String keyWord, Integer people, String start, String end) throws InvocationTargetException, IllegalAccessException;
}
