package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {

    List<OrderDetail> findAllByRoomId(int roomId);

    @Query(value = "SELECT ORDER_COMMENT FROM ORDER_DETAIL WHERE ROOM_ID = ?1", nativeQuery = true)
    List<String> findCommentByRoomId(Integer roomId);

    @Query(value = "SELECT ORDER_RANK FROM ORDER_DETAIL WHERE ROOM_ID = ?1", nativeQuery = true)
    public List<Integer> findRoomRank(Integer roomId);
}
