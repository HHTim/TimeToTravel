package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.dto.RoomWithCompanyDto;
import com.tibame.timetotravel.view.ViewCompanyRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewCompanyRoomRepository extends CrudRepository<ViewCompanyRoom, Integer> {
    @Query(value = "SELECT * FROM VIEW_COMPANY_ROOM WHERE ROOM_ID = ?1", nativeQuery = true)
    ViewCompanyRoom findForPaid(Integer roomId);

    /**
     * 根據輸入的關鍵字查符合的商家回傳結果集合
     */
    @Query(value = "SELECT * FROM VIEW_COMPANY_ROOM WHERE (COM_ADDRESS LIKE %?1% OR COM_NAME LIKE %?1%) AND (ROOM_PEOPLE = ?2) AND (ROOM_STATUS = 1) ORDER BY ROOM_ID DESC", nativeQuery = true)
    List<ViewCompanyRoom> findCompany(String keyWord, Integer people);

    ViewCompanyRoom findByComIdAndRoomId(Integer comId, Integer roomId);

    ViewCompanyRoom findByRoomId(Integer roomId);

    @Query(value = "SELECT new com.tibame.timetotravel.dto.RoomWithCompanyDto(r.roomId, r.roomName, c.comName, c.comAddress) FROM Room r JOIN Company c ON r.comId = c.comId WHERE r.roomId = ?1")
    RoomWithCompanyDto findJoin(Integer roomId);
}
