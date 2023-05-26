package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("CompanyRepository")
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
