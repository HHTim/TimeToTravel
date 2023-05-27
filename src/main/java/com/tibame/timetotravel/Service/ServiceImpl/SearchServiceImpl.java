package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.SearchRoom;
import com.tibame.timetotravel.repository.SearchRepository;
import com.tibame.timetotravel.service.SearchService;
import com.tibame.timetotravel.view.ViewCompanyRoom;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchRepository searchRepository;


    @Override
    public List<SearchRoom> findAvailableCompany(String keyWord, Integer people, String start, String end) throws InvocationTargetException, IllegalAccessException {
        List<ViewCompanyRoom> companies = searchRepository.findCompany(keyWord, people);
        List<SearchRoom> resultList = new ArrayList<>();

        for (ViewCompanyRoom company : companies) {
            // 每次獲取一個新的searchRoom Bean
            SearchRoom searchRoom = new SearchRoom();
            // 取得房間的房型編號
            Integer roomId = company.getRoomId();
            // 透過房型編號跟時間區間去查該段時間的訂單數
            Integer orderCount = searchRepository.findRoomStock(roomId, start, end);
//            System.out.println("房型編號: " + roomId);
//            System.out.println(start + " " + end + " 期間訂單數: " + orderCount);
            // 如果庫存數大於訂單數則將該房間加入
            if (company.getRoomStock() > orderCount) {
                // Common Util 複製Entity到DTO
                BeanUtils.copyProperties(searchRoom, company);
                List<Integer> roomRank = searchRepository.findRoomRank(roomId);
                searchRoom.setOrderRanks(roomRank);
                resultList.add(searchRoom);
            }
        }
        return resultList;
    }
}
