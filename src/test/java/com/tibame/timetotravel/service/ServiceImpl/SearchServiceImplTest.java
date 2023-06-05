package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.SearchRoomDto;
import com.tibame.timetotravel.service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@SpringBootTest
class SearchServiceImplTest {

    @Autowired
    SearchService searchService;

    @Test
    public void findAvailableCompanyTest() throws InvocationTargetException, IllegalAccessException {
        PageBean<SearchRoomDto> companies = searchService.findAvailableCompany("新北市", 1, "2023-05-01", "2023-05-05", 1);
    }

    @Test
    public void findNearSceneRoomsTest() throws InvocationTargetException, IllegalAccessException {
        List<SearchRoomDto> rooms = searchService.findNearSceneRooms("新北", 1, "2023-06-01", "2023-06-05");
        for (SearchRoomDto room : rooms) {
            System.out.println(room.getRoomId());
        }
    }
}