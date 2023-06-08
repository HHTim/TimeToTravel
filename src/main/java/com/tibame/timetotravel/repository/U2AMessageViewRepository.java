package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.U2AMsgView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("U2AMessageViewRepository")
public interface U2AMessageViewRepository extends JpaRepository<U2AMsgView,Integer> {

    @Query(value = "SELECT * FROM VIEW_U2A_MSG ORDER BY U2A_SENDING_TIME DESC LIMIT ?1,?2",nativeQuery = true)
    List<U2AMsgView> findViewByPage(Integer currPage, Integer rows);

    @Query(value = "SELECT COUNT(*) FROM VIEW_U2A_MSG WHERE USER_NAME like %?1% ORDER BY U2A_SENDING_TIME DESC",nativeQuery = true)
    Integer findViewByKeyWords(String keyword);

    @Query(value = "SELECT * FROM VIEW_U2A_MSG WHERE USER_NAME like %?1% ORDER BY U2A_SENDING_TIME DESC LIMIT ?2,?3",nativeQuery = true)
    List<U2AMsgView> findViewByKeyWordsPage(String keyword, Integer currPage, Integer rows);

    @Query(value = "SELECT COUNT(*) FROM VIEW_U2A_MSG where U2A_SENDING_TIME between ?1 and ?2 ORDER BY U2A_SENDING_TIME DESC",nativeQuery = true)
    Integer findViewByDateRange(String startDate, String endDate);

    @Query(value = "SELECT * FROM VIEW_U2A_MSG where U2A_SENDING_TIME between ?1 and ?2 ORDER BY U2A_SENDING_TIME DESC LIMIT ?3,?4",nativeQuery = true)
    List<U2AMsgView> findViewByDateRangePage(String startDate, String endDate, Integer currPage, Integer rows);

    @Query(value = "SELECT * FROM VIEW_U2A_MSG ORDER BY U2A_SENDING_TIME DESC LIMIT ?1,?2",nativeQuery = true)
    List<U2AMsgView> findU2AViewMsgByUsers(Integer startIndex, Integer endIndex);
}
