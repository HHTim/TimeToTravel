package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.AnnView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("adminAnnViewRepository")
public interface AdminAnnViewRepository extends JpaRepository<AnnView,Integer> {

}
