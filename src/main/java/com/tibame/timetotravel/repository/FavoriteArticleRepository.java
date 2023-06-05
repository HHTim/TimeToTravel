package com.tibame.timetotravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tibame.timetotravel.entity.FavoriteArticle;

@Repository("favoriteArticleRepository")
public interface FavoriteArticleRepository extends JpaRepository<FavoriteArticle, Integer> {

	//
	@Query(value = "select * from favorite_article where post_id = ?1 and user_id = ?2 ", nativeQuery = true)
	FavoriteArticle checkFavorArticle(Integer postId, Integer userId);
	// 確認收藏存在 與上面相同
	FavoriteArticle findByUserIdAndPostId(Integer userId,Integer postId);

	
	List<FavoriteArticle> findByUserId(Integer userId);
	
	void deleteByPostId(Integer postId);
	
}
