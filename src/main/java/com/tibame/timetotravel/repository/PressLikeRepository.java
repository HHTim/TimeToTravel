package com.tibame.timetotravel.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tibame.timetotravel.entity.PressLike;

@Repository("pressLikeRepository")
public interface PressLikeRepository extends JpaRepository<PressLike, Integer> {

	// 按讚 insert
	// 移除 delete
	// 注意 有兩個異動 按讚表 BLOG 資料更動
	 @Query(value = "select * from press_like where post_id = ?1 and user_id = ?2 ",nativeQuery = true)
	    PressLike checkLike(Integer postId,Integer user_id);
	
}
