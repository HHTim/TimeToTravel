package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUserId(Integer userId);
    User findByUserAccount(String userAccount);
    @Modifying
    @Query(value = "UPDATE USER JOIN (SELECT USER_ID FROM USER WHERE USER_ACCOUNT = ?1) AS subquery SET USER_STATUS = ?2 WHERE USER.USER_ID = subquery.USER_ID", nativeQuery = true)
    void updateUserStatus(String Account, Integer status);

    @Modifying
    @Query(value = "UPDATE USER JOIN (SELECT USER_ID FROM USER WHERE USER_ACCOUNT = ?1) AS subquery SET USER_NEWS_STATUS = ?2 WHERE USER.USER_ID = subquery.USER_ID", nativeQuery = true)
    void updateUserNewsStatus(String Account, Integer newsStatus);

    @Modifying
    @Query(value = "UPDATE USER SET USER_PASSWORD = ?1 WHERE USER_ID = ?2", nativeQuery = true)
    void updateUserPassword(String password, Integer userId);

    @Query(value = "SELECT * FROM USER ORDER BY USER_SIGN_DATETIME DESC LIMIT ?1,?2",nativeQuery = true)
    List<User> findByPage(Integer currPage, Integer rows);

    @Query(value = "SELECT COUNT(*) FROM USER WHERE USER_STATUS = ?1 ORDER BY USER_SIGN_DATETIME DESC", nativeQuery = true)
    Integer findAllStatus(Integer status);

    @Query(value = "SELECT * FROM USER WHERE USER_STATUS = ?1 ORDER BY USER_SIGN_DATETIME DESC LIMIT ?2,?3",nativeQuery = true)
    List<User> findStatusByPage(Integer status, Integer currPage, Integer rows);

    @Query(value = "SELECT COUNT(*) FROM USER WHERE USER_ACCOUNT LIKE %?1% OR USER_NAME LIKE %?1% ORDER BY USER_SIGN_DATETIME DESC", nativeQuery = true)
    Integer findAllByKeword(String keyword);

    @Query(value = "SELECT * FROM USER WHERE USER_ACCOUNT LIKE %?1% OR USER_NAME LIKE %?1% ORDER BY USER_SIGN_DATETIME DESC LIMIT ?2,?3", nativeQuery = true)
    List<User> findKeywordByPage(String keyword, Integer currPage, Integer rows);

    @Query(value = "SELECT COUNT(*) FROM USER WHERE USER_SIGN_DATETIME between ?1 AND ?2 ORDER BY USER_SIGN_DATETIME DESC", nativeQuery = true)
    Integer findAllByDateRange(String startDate, String endDate);

    @Query(value = "SELECT * FROM USER WHERE USER_SIGN_DATETIME between ?1 AND ?2 ORDER BY USER_SIGN_DATETIME DESC LIMIT ?3,?4", nativeQuery = true)
    List<User> findDateRangeByPage(String startDate, String endDate, Integer currPage, Integer rows);
    
}
