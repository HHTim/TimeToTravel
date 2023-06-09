package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.repository.ViewGiftOrderManageRepository;
import com.tibame.timetotravel.service.GiftOrderManageService;
import com.tibame.timetotravel.view.ViewGiftOrderManage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;


@Service("giftOrderManageService")
    public class GiftOrderManageServiceImpl implements GiftOrderManageService {

    @Autowired
    @Qualifier("viewGiftOrderManageRepository")
    private ViewGiftOrderManageRepository viewGiftOrderManageRepository;

    @PersistenceContext
    private EntityManager entityManager;


//    @Override
//    public  ViewGiftOrderManage findById(Integer giftOrderId) {
//        return null;
//    }

    @Override
    public List<ViewGiftOrderManage> findAll() {
        return viewGiftOrderManageRepository.findAll();
    }

    @Override
    public List<ViewGiftOrderManage> findByKeyword(String keywordId, String keywordAccount) {
        return viewGiftOrderManageRepository.findByKeyword(keywordId, keywordAccount);
    }

//    @Override
//    public List<ViewGiftOrderManage> findByGiftOrderId(Integer giftOrderId) {
//        return viewGiftOrderManageRepository.findByGiftOrderId(giftOrderId);
//    }
//
//    @Override
//    public List<ViewGiftOrderManage> findByUserAccount(String userAccount) {
//        return viewGiftOrderManageRepository.findByUserAccount(userAccount);
//    }

//    @Override
//    public List<ViewGiftOrderManage> findByGiftOrderIdOrUserAccount(Integer giftOrderId, String userAccount) {
////        return viewGiftOrderManageRepository.findByGiftOrder(giftOrderId, userId);
//        if (giftOrderId != null) {  // 只根据 giftOrderId 查询
//            return viewGiftOrderManageRepository.findByGiftOrderId(giftOrderId);
//        } else if (userAccount != null) { // 只根据 userId 查询
//            return viewGiftOrderManageRepository.findByUserAccount(userAccount);
//        } else {
//            // 如果没有提供任何参数，你可以返回所有订单或者做其他处理
//            return viewGiftOrderManageRepository.findAll();
//        }
//    }

    @Override
    public List<ViewGiftOrderManage> findByDateRange(String startDate, String endDate) {
        return viewGiftOrderManageRepository.findByDateRange(startDate, endDate);
    }


}
