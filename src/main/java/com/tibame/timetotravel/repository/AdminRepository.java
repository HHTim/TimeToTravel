package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository("AdminRepository")
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Admin findByAdminId(Integer id);
    Admin findByAdminName(String adminName);
    Admin findByAdminAccount(String adminAccount);

    @Modifying
    @Query(value = "UPDATE ADMIN JOIN (SELECT ADMIN_ID FROM ADMIN WHERE ADMIN_ACCOUNT = ?1) AS subquery SET ADMIN_NEWS_STATUS = ?2 WHERE ADMIN.ADMIN_ID = subquery.ADMIN_ID", nativeQuery = true)
    void updateAdminNewsStatus(String Account, Integer newsStatus);
}
