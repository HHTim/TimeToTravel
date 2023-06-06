package com.tibame.timetotravel.service.ServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tibame.timetotravel.entity.ArticleComment;
import com.tibame.timetotravel.entity.Blog;
import com.tibame.timetotravel.repository.ArticleCommentRepository;
import com.tibame.timetotravel.repository.BlogRepository;

@Service("articleCommentServiceImpl")
public class ArticleCommentServiceImpl {

	@Autowired
	@Qualifier("articleCommentRepository")
	private ArticleCommentRepository articleCommentRepository;

	@Autowired
	@Qualifier("blogRepository")
	private BlogRepository blogRepository;

	// 刪除留言
	public String deleteComment(ArticleComment comment) {
		articleCommentRepository.deleteById(comment.getCommentNo());
		Blog blog = blogRepository.findByPostId(comment.getPostId());
		blog.setComments(blog.getComments() - 1);
		blogRepository.save(blog);
		return "delete 留言";
	}

	// 新增留言
	public ArticleComment insertComment(ArticleComment comment) {
		comment.setCommentDatetime(new Timestamp(System.currentTimeMillis()));
		ArticleComment articleComment = articleCommentRepository.save(comment);
		Blog blog = blogRepository.findByPostId(comment.getPostId());
		blog.setComments(blog.getComments() + 1);
		blogRepository.save(blog);
		return articleComment;
	}

	// 修改留言
	public String updateComment(ArticleComment comment) {
		comment.setCommentDatetime(
				articleCommentRepository.findById(comment.getCommentNo()).orElse(null).getCommentDatetime());
//		System.out.println("22222222222222222");
		articleCommentRepository.save(comment);
		return "update 留言";
	}
}
