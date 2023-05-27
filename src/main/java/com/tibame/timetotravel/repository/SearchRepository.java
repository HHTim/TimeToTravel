package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.ViewCompanyRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("searchRepository")
public interface SearchRepository extends CrudRepository<ViewCompanyRoom, Integer> {

    String findRoomStock = " SELECT COUNT(1) FROM ORDER_DETAIL " +
            "WHERE " +
            "ROOM_ID = ?1 AND ((ORDER_CHECK_IN BETWEEN ?2 AND ?3) OR " +
            "(ORDER_CHECK_OUT BETWEEN ?2 AND ?3) OR " +
            "(?2 BETWEEN ORDER_CHECK_IN AND ORDER_CHECK_OUT) OR " +
            "(?3 BETWEEN ORDER_CHECK_IN AND ORDER_CHECK_OUT));";

    /**
     * 根據輸入的關鍵字查符合的商家回傳結果集合
     */
    @Query(value = "SELECT * FROM VIEW_COMPANY_ROOM WHERE (COM_ADDRESS LIKE ?1%) AND (ROOM_PEOPLE = ?2) AND (ROOM_STATUS = 1)", nativeQuery = true)
    public List<ViewCompanyRoom> findCompany(String keyWord, Integer people);

    /**
     * 將查出來的商家集合跑迴圈，取出他們的房型編號後查輸入的時間區間他有幾張訂單
     * 如果訂單的數量大於等於商家的某間房型的庫存數則將之從結果集合刪除
     */
    @Query(value = findRoomStock, nativeQuery = true)
    public Integer findRoomStock(Integer roomId, String start, String end);

    /**
     * 根據傳進來的roomId查詢房間的所有評價，回傳一個所有評價的集合
     */
    @Query(value = "SELECT ORDER_RANK FROM ORDER_DETAIL WHERE ROOM_ID = ?1", nativeQuery = true)
    public List<Integer> findRoomRank(Integer roomId);
}
