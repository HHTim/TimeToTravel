package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.dto.OrderListDto;
import com.tibame.timetotravel.dto.OrderWithUser;
import com.tibame.timetotravel.entity.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {
    String findOrderByDate = " SELECT COUNT(1) FROM ORDER_DETAIL " +
            "WHERE " +
            "ROOM_ID = ?1 AND ((ORDER_CHECK_IN BETWEEN ?2 AND ?3) OR " +
            "(ORDER_CHECK_OUT BETWEEN ?2 AND ?3) OR " +
            "(?2 BETWEEN ORDER_CHECK_IN AND ORDER_CHECK_OUT) OR " +
            "(?3 BETWEEN ORDER_CHECK_IN AND ORDER_CHECK_OUT));";


    //    將查出來的商家集合跑迴圈，取出他們的房型編號後查輸入的時間區間他有幾張訂單
    //    如果訂單的數量大於等於商家的某間房型的庫存數則將之從結果集合刪除
    @Query(value = findOrderByDate, nativeQuery = true)
    Integer findOrderByDate(Integer roomId, String start, String end);

    @Query(value = "SELECT new com.tibame.timetotravel.dto.OrderWithUser(u.userName, u.userAvatar, o.orderRank, o.orderComment, o.orderDateTime, r.roomName) FROM User u JOIN OrderDetail o ON u.userId = o.userId JOIN Room r ON o.roomId = r.roomId JOIN Company c ON r.comId = c.comId WHERE c.comId = :comId ORDER BY o.orderDateTime")
    List<OrderWithUser> findOrderWithUserByComId(@Param("comId") Integer comId);

    @Query(value = "SELECT new com.tibame.timetotravel.dto.OrderListDto(c.comId, r.roomId, o.orderId, c.comName, r.roomName, r.roomBed, o.orderCheckIn, o.orderCheckOut, r.roomPrice, j.journeyName, j.journeyPrice, o.orderDateTime, o.orderAmount) FROM OrderDetail o JOIN Room r ON o.roomId = r.roomId JOIN Company c ON c.comId = r.comId JOIN User u ON o.userId = u.userId LEFT JOIN Journey j ON o.journeyId = j.journeyId")
    List<OrderListDto> findUserOrder();
}