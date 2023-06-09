package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.ViewGiftOrderManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewGiftOrderManageRepository extends JpaRepository<ViewGiftOrderManage, Integer> {

//    @Query(value = "SELECT * FROM View_Gift_Order_Manage WHERE GIFT_ORDER_DETAILS_ID LIKE  %?1%" , nativeQuery = true)
//    List<ViewGiftOrderManage> findByGiftOrderDetailsId(Integer giftOrderDetailsId);

//    @Query(value = "SELECT * FROM View_Gift_Order_Manage WHERE GIFT_ORDER_ID LIKE ?1 OR USER_ID LIKE ?2" , nativeQuery = true)
//    List<ViewGiftOrderManage> findByGiftOrder(Integer giftOrderId, Integer userId);

//    @Query(value = "SELECT * FROM View_Gift_Order_Manage WHERE GIFT_ORDER_ID = ?1 || USER_ACCOUNT LIKE ?2", nativeQuery = true)
//    List<ViewGiftOrderManage> findByGiftOrderId(Integer giftOrderId, String userAccount);

//    @Query(value = "SELECT * FROM View_Gift_Order_Manage WHERE GIFT_ORDER_ID = ?1 ", nativeQuery = true)
//    List<ViewGiftOrderManage> findByGiftOrderId(Integer giftOrderId);
//
//    @Query(value = "SELECT * FROM View_Gift_Order_Manage WHERE USER_ACCOUNT LIKE %?1% ", nativeQuery = true)
//    List<ViewGiftOrderManage> findByUserAccount(String userAccount);
    @Query(value = "SELECT * FROM View_Gift_Order_Manage WHERE GIFT_ORDER_ID = ?1 || USER_ACCOUNT LIKE %?2%", nativeQuery = true)
    List<ViewGiftOrderManage> findByKeyword(String keywordId, String keywordAccount);

    @Query(value = "SELECT * FROM view_gift_order_manage WHERE GIFT_ORDER_DATETIME BETWEEN ?1 AND ?2 ORDER BY GIFT_ORDER_DATETIME DESC ", nativeQuery = true)
    List<ViewGiftOrderManage> findByDateRange(String startDate, String endDate);



}