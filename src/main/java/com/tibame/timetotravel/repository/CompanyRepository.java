package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("CompanyRepository")
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company findByComName(String comName);
    Company findByComAccount(String comAccount);
    Company findByComTaxId(String comTaxId);

    @Modifying
    @Query(value = "UPDATE COMPANY JOIN (SELECT COM_ID FROM COMPANY WHERE COM_NAME = ?1) AS subquery SET COM_STATUS = ?2 WHERE COMPANY.COM_ID = subquery.COM_ID", nativeQuery = true)
    void updateCompanyStatus(String comName, Integer status);

    @Modifying
    @Query(value = "UPDATE COMPANY JOIN (SELECT COM_ID FROM COMPANY WHERE COM_ACCOUNT = ?1) AS subquery SET COM_NEWS_STATUS = ?2 WHERE COMPANY.COM_ID = subquery.COM_ID", nativeQuery = true)
    void updateCompanyNewsStatus(String Account, Integer newsStatus);

    @Modifying
    @Query(value = "UPDATE COMPANY SET COM_PASSWORD = ?1 WHERE COM_ID = ?2", nativeQuery = true)
    void updateCompanyPassword(String password, Integer comId);
    @Query(value = "SELECT * FROM COMPANY ORDER BY COM_SIGNDATE DESC LIMIT ?1,?2",nativeQuery = true)
    List<Company> findByPage(Integer currPage, Integer limit);

    @Query(value = "SELECT COUNT(*) FROM COMPANY WHERE COM_STATUS = ?1 ORDER BY COM_SIGNDATE DESC",nativeQuery = true)
    Integer findAllStatus(Integer status);

    @Query(value = "SELECT * FROM COMPANY WHERE COM_STATUS = ?1 ORDER BY COM_SIGNDATE DESC LIMIT ?2,?3",nativeQuery = true)
    List<Company> findStatusByPage(Integer status, Integer currPage, Integer limit);

    @Query(value = "SELECT COUNT(*) FROM COMPANY WHERE COM_ACCOUNT like %?1% ORDER BY COM_SIGNDATE DESC",nativeQuery = true)
    Integer findAllByAccountKeword(String keyword);

    @Query(value = "SELECT * FROM COMPANY WHERE COM_ACCOUNT like %?1% ORDER BY COM_SIGNDATE DESC LIMIT ?2,?3",nativeQuery = true)
    List<Company> findAccountKeywordByPage(String keyword, Integer currPage, Integer rows);

    @Query(value = "SELECT COUNT(*) FROM COMPANY WHERE COM_NAME like %?1% ORDER BY COM_SIGNDATE DESC",nativeQuery = true)
    Integer findAllByComNameKeword(String keyword);

    @Query(value = "SELECT * FROM COMPANY WHERE COM_NAME like %?1% ORDER BY COM_SIGNDATE DESC LIMIT ?2,?3",nativeQuery = true)
    List<Company> findComNameKeywordByPage(String keyword, Integer currPage, Integer rows);

    @Query(value = "SELECT COUNT(*) FROM COMPANY WHERE COM_MANAGER like %?1% ORDER BY COM_SIGNDATE DESC",nativeQuery = true)
    Integer findAllByComManagerKeword(String keyword);

    @Query(value = "SELECT * FROM COMPANY WHERE COM_MANAGER like %?1% ORDER BY COM_SIGNDATE DESC LIMIT ?2,?3",nativeQuery = true)
    List<Company> findComManagerKeywordByPage(String keyword, Integer currPage, Integer rows);
    
}
