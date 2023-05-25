package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.A2CMsgView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("A2CMessageViewRepository")
public interface A2CMessageViewRepository extends JpaRepository<A2CMsgView,Integer> {

    @Query(value = "SELECT * FROM VIEW_A2C_MSG ORDER BY A2C_SENDING_TIME DESC LIMIT ?1,?2",nativeQuery = true)
    List<A2CMsgView> findViewByPage(Integer currPage, Integer limit);
}
