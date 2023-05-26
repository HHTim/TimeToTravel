package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("articleCommentRepository")
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Integer> {

	
//	@Query(value = "SELECT POST_ID,USER_ID,POST_TITLE,POST_CONTENT,POST_DATE,LIKES,POST_PHOTO,POST_TYPE_ID,POST_UPDATE_TIME,COMMENTS FROM BLOG where POST_ID = ?1 ", nativeQuery = true)
//	Blog findByPostId(Integer postId);
	
	@Query(value = "SELECT * from article_comment where post_id = ?1", nativeQuery = true)
	List<ArticleComment> findByPostId(Integer postId);
}
