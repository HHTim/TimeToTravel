package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.A2UMsgView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("A2UMessageViewRepository")
public interface A2UMessageViewRepository extends JpaRepository<A2UMsgView,Integer> {

    @Query(value = "SELECT * FROM VIEW_A2U_MSG ORDER BY A2U_SENDING_TIME DESC LIMIT ?1,?2",nativeQuery = true)
    List<A2UMsgView> findViewByPage(Integer currPage, Integer limit);
}
