package com.tibame.timetotravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tibame.timetotravel.view.ArticleCommentView;

@Repository("articleCommentViewRepository")
public interface ArticleCommentViewRepository extends JpaRepository<ArticleCommentView, Integer> {

	@Query(value = "SELECT * FROM view_article_comment WHERE post_id = ?", nativeQuery = true)
	List<ArticleCommentView> findByPostId(Integer postId);
}
