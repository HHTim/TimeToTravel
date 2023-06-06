package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.A2UMsgView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("A2UMessageViewRepository")
public interface A2UMessageViewRepository extends JpaRepository<A2UMsgView,Integer> {

    @Query(value = "SELECT * FROM VIEW_A2U_MSG ORDER BY A2U_SENDING_TIME DESC LIMIT ?1,?2",nativeQuery = true)
    List<A2UMsgView> findViewByPage(Integer currPage, Integer rows);

    @Query(value = "SELECT COUNT(*) FROM VIEW_A2U_MSG where A2U_SENDING_TIME between ?1 and ?2 ORDER BY A2U_SENDING_TIME DESC",nativeQuery = true)
    Integer findViewByDateRange(String startDate, String endDate);

    @Query(value = "SELECT * FROM VIEW_A2U_MSG where A2U_SENDING_TIME between ?1 and ?2 ORDER BY A2U_SENDING_TIME DESC LIMIT ?3,?4",nativeQuery = true)
    List<A2UMsgView> findViewByDateRangePage(String startDate, String endDate, Integer currPage, Integer rows);


    @Query(value = "SELECT * FROM VIEW_A2U_MSG where USER_ID = ?1 ORDER BY A2U_SENDING_TIME DESC LIMIT ?2,?3",nativeQuery = true)
    List<A2UMsgView> findA2UViewMsgByUserId(Integer userId,Integer startIndex, Integer endIndex);
}
