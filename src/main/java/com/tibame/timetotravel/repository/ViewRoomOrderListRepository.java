package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.ViewRoomOrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewRoomOrderListRepository extends CrudRepository<ViewRoomOrderDetail, Integer> {

    @Query(value = "SELECT * FROM VIEW_ROOM_ORDER_DETAIL WHERE USER_ID = ?1 ORDER BY ORDER_DATETIME DESC", nativeQuery = true)
    List<ViewRoomOrderDetail> findByUserId(Integer userId);

    List<ViewRoomOrderDetail> findByUserIdAndOrderId(Integer userId, Integer orderId);

    @Query(value = "SELECT O.* FROM ROOM R JOIN ORDER_DETAIL O ON R.ROOM_ID = O.ROOM_ID WHERE R.COM_ID = ?1 LIMIT ?2, ?3", nativeQuery = true)
    List<ViewRoomOrderDetail> findByComId(Integer comId);

}
