package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.C2AMsgView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("C2AMessageViewRepository")
public interface C2AMessageViewRepository extends JpaRepository<C2AMsgView,Integer> {

    @Query(value = "SELECT * FROM VIEW_C2A_MSG ORDER BY C2A_SENDING_TIME DESC LIMIT ?1,?2",nativeQuery = true)
    List<C2AMsgView> findViewByPage(Integer currPage, Integer rows);

    @Query(value = "SELECT COUNT(*) FROM VIEW_C2A_MSG WHERE COM_NAME like %?1% ORDER BY C2A_SENDING_TIME DESC",nativeQuery = true)
    Integer findViewByKeyWords(String keyword);

    @Query(value = "SELECT * FROM VIEW_C2A_MSG WHERE COM_NAME like %?1% ORDER BY C2A_SENDING_TIME DESC LIMIT ?2,?3",nativeQuery = true)
    List<C2AMsgView> findViewByKeyWordsPage(String keyword, Integer currPage, Integer rows);

    @Query(value = "SELECT COUNT(*) FROM VIEW_C2A_MSG where C2A_SENDING_TIME between ?1 and ?2 ORDER BY C2A_SENDING_TIME DESC",nativeQuery = true)
    Integer findViewByDateRange(String startDate, String endDate);

    @Query(value = "SELECT * FROM VIEW_C2A_MSG where C2A_SENDING_TIME between ?1 and ?2 ORDER BY C2A_SENDING_TIME DESC LIMIT ?3,?4",nativeQuery = true)
    List<C2AMsgView> findViewByDateRangePage(String startDate, String endDate,Integer currPage, Integer rows);

    @Query(value = "SELECT * FROM VIEW_C2A_MSG ORDER BY C2A_SENDING_TIME DESC LIMIT ?1,?2",nativeQuery = true)
    List<C2AMsgView> findC2AViewMsgByComps(Integer startIndex, Integer endIndex);
}
