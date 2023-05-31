package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.ViewUserOrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ViewUserOrderDetailRepository extends CrudRepository<ViewUserOrderDetail, Integer> {

    List<ViewUserOrderDetail> findAllByRoomId(int roomId);

    @Query(value = "SELECT ORDER_COMMENT FROM ORDER_DETAIL WHERE ROOM_ID = ?1", nativeQuery = true)
    List<String> findCommentByRoomId(Integer roomId);

    @Query(value = "SELECT ORDER_RANK FROM ORDER_DETAIL WHERE ROOM_ID = ?1", nativeQuery = true)
    List<Integer> findRankByRoomId(Integer roomId);
}
