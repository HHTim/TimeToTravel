package com.tibame.timetotravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tibame.timetotravel.dto.ArticleCommentDTO;
import com.tibame.timetotravel.entity.ArticleComment;

@Repository("articleCommentRepository")
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Integer> {

	@Query(value = "SELECT * from article_comment where post_id = ?1", nativeQuery = true)
	List<ArticleComment> findByPostId(Integer postId);

	// 不知道底錯三小 先不用DTO方式
//	@Query(value = "select new com.tibame.timetotravel.dto.ArticleCommentDTO(ac.COMMENT_NO, ac.POST_ID, ac.USER_ID, ac.COMMENT_CONTENT, ac.COMMENT_DATETIME, u.USER_NAME) FROM  article_comment ac JOIN user u ON ac.USER_ID = u.USER_ID Where ac.POST_ID = :postId ")
//	List<ArticleCommentDTO> findByPostIdDTO(@Param("postId") Integer postId);
	/*
	 * @Query(value =
	 * "select new com.tibame.timetotravel.dto.GiftOrderList(go.giftOrderId, u.userName, g.giftName, g.giftPrice, god.boughtCount, god.unitPrice, go.giftOrderDatetime, go.giftOrderStatus, go.giftOrderAmount) from GiftOrder go left join User u on go.userId = u.userId left join GiftOrderDetails god on go.giftOrderId = god.giftOrderId left join Gift g on god.giftId = g.giftId"
	 * ) List<GiftOrderList> findAllGiftOrders();
	 */

	void deleteByPostId(Integer postId);
}
