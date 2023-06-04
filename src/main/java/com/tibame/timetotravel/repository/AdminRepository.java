package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("AdminRepository")
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Admin findByAdminId(Integer id);

    Admin findByAdminAccount(String adminAccount);


}
