package com.tibame.timetotravel.service.ServiceImpl;

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
        List<SearchRoomDto> companies = searchService.findAvailableCompany("新北市", 1, "2023-05-01", "2023-05-05");
        System.out.println("共有: " + companies.size() + " 間符合條件");

        for (SearchRoomDto company : companies) {
            System.out.println(company);
        }
    }
}