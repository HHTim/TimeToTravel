package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.Ann;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("adminAnnRepository")
public interface AdminAnnRepository extends JpaRepository<Ann,Integer> {

    /*方法一 : 優先使用findByXXX的命名規則*/

    /*SELECT * FROM AnnVO WHERE name = ?*/
//    List<AnnVO> findByName(String name);

    /*SELECT * FROM AnnVO WHERE id = ? AND name = ?*/
//    AnnVO findByIdAndName(Integer id, String name);

    /*方法二 :自定義條件查詢，使用原生SQL*/
    @Query(value = "SELECT * FROM ANN ORDER BY ANN_SENDING_TIME DESC LIMIT ?1,?2",nativeQuery = true)
    List<Ann> findByPage(Integer currPage, Integer rows);

    @Query(value = "SELECT * FROM ANN where ANN_SENDING_TIME between ?1 and ?2 ORDER BY ANN_SENDING_TIME DESC LIMIT ?3,?4",nativeQuery = true)
    List<Ann> findByDateRange(String startDate, String endDate,Integer currPage, Integer rows);

    @Query(value = "SELECT COUNT(*) FROM ANN where ANN_SENDING_TIME between ?1 and ?2 ORDER BY ANN_SENDING_TIME DESC",nativeQuery = true)
    Integer findAllDateRange(String startDate, String endDate);

    @Query(value = "SELECT * FROM ANN where ANN_TITLE like %?1% ORDER BY ANN_SENDING_TIME DESC LIMIT ?2,?3",nativeQuery = true)
    List<Ann> findByKeyWords(String keyword, Integer currPage, Integer rows);

    @Query(value = "SELECT COUNT(*) FROM ANN where ANN_TITLE like %?1% ORDER BY ANN_SENDING_TIME DESC",nativeQuery = true)
    Integer findByAllKeyWords(String keyword);


}
