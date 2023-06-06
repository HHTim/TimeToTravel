package com.tibame.timetotravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tibame.timetotravel.entity.ArticleTags;

@Repository("articleTagsRepository")
public interface ArticleTagsRepository extends JpaRepository<ArticleTags, Integer> {

	// 一篇文章有的標籤
	@Query(value = "SELECT * FROM ARTICLE_TAG where POST_ID = ?1 ", nativeQuery = true)
	List<ArticleTags> findByPostId(Integer postId);

	int countByTagId(Integer tagId);

	ArticleTags findByPostIdAndTagId(Integer postId, Integer tagId);
	
	void deleteByPostId(Integer postId);

}
