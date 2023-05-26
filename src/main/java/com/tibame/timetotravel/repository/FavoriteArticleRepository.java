package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.FavoriteArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("favoriteArticleRepository")
public interface FavoriteArticleRepository extends JpaRepository<FavoriteArticle, Integer>{

	
	// 
	 @Query(value = "select * from press_like where post_id = ?1 and user_id = ?2 ",nativeQuery = true)
	    FavoriteArticle checkFavorArticle(Integer postId,Integer userId);
	
}
