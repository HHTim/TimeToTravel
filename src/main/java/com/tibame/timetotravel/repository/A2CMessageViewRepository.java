package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.A2CMsgView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("A2CMessageViewRepository")
public interface A2CMessageViewRepository extends JpaRepository<A2CMsgView,Integer> {

    @Query(value = "SELECT * FROM VIEW_A2C_MSG ORDER BY A2C_SENDING_TIME DESC LIMIT ?1,?2",nativeQuery = true)
    List<A2CMsgView> findViewByPage(Integer currPage, Integer rows);

    @Query(value = "SELECT COUNT(*) FROM VIEW_A2C_MSG where A2C_SENDING_TIME between ?1 and ?2 ORDER BY A2C_SENDING_TIME DESC",nativeQuery = true)
    Integer findViewByDateRange(String startDate, String endDate);

    @Query(value = "SELECT * FROM VIEW_A2C_MSG where A2C_SENDING_TIME between ?1 and ?2 ORDER BY A2C_SENDING_TIME DESC LIMIT ?3,?4",nativeQuery = true)
    List<A2CMsgView> findViewByDateRangePage(String startDate, String endDate, Integer currPage, Integer rows);

    @Query(value = "SELECT * FROM VIEW_A2C_MSG where COM_ID = ?1 ORDER BY A2C_SENDING_TIME DESC LIMIT ?2,?3",nativeQuery = true)
    List<A2CMsgView> findA2CViewMsgByComId(Integer comId, Integer startIndex, Integer endIndex);
}
