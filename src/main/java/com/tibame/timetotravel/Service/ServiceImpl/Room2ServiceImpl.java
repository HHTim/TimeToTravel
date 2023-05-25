package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.repository.Room2Repository;
import com.tibame.timetotravel.service.Room2Service;
import com.tibame.timetotravel.view.ViewCompanyRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Room2ServiceImpl implements Room2Service {

    @Autowired
    Room2Repository room2Repository;

    @Override
    public List<ViewCompanyRoom> findAvailableCompany(String keyWord, Integer people, String start, String end) {
        List<ViewCompanyRoom> companies = room2Repository.findCompany(keyWord, people);
        List<ViewCompanyRoom> resultList = new ArrayList<>();

        for (ViewCompanyRoom company : companies) {
            // 取得房間的房型編號
            Integer roomId = company.getRoomId();
            // 透過房型編號跟時間區間去查該段時間的訂單數
            Integer orderCount = room2Repository.findRoomStock(roomId, start, end);
            System.out.println("房型編號: " + roomId);
            System.out.println(start + " " + end + " 期間訂單數: " + orderCount);
            // 如果訂單數大於等於庫存數則將該房間踢掉
            if (company.getRoomStock() > orderCount) {
                resultList.add(company);
            }
        }
        return resultList;
    }
}
