package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.entity.ArticleComment;
import com.tibame.timetotravel.entity.Blog;
import com.tibame.timetotravel.entity.FavoriteArticle;
import com.tibame.timetotravel.entity.PressLike;
import com.tibame.timetotravel.repository.*;
import com.tibame.timetotravel.view.DefaultBlogView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("blogService")
public class BlogServiceImpl {
	@Autowired
	@Qualifier("blogRepository")
	private BlogRepository blogRepository;
	@Autowired
	@Qualifier("defaultBlogRepository")
	private DefaultBlogRepository defaultBlogRepository;
	@Autowired
	@Qualifier("articleCommentRepository")
	private ArticleCommentRepository articleCommentRepository;
	@Autowired
	@Qualifier("pressLikeRepository")
	private PressLikeRepository pressLikeRepository;
	@Autowired
	@Qualifier("favoriteArticleRepository")
	private FavoriteArticleRepository favoriteArticleRepository;

	// Blog 資料
	public DefaultBlogView getBlogView(Integer postId) {
		Optional<DefaultBlogView> blog = defaultBlogRepository.findById(postId);
		if (blog.isPresent()) {
			return (DefaultBlogView) blog.get();
		}
		return null;
	}

	// 取得 單一BLOG 所有留言
	public List<ArticleComment> findArticleComments(Integer postId) {
		return articleCommentRepository.findByPostId(postId);
	}

	public int likeBlog(PressLike pressLike) {
		// 查 存在
		PressLike bool = pressLikeRepository.checkLike(pressLike.getPostId(), pressLike.getUserId());
		if (bool== null) {
			// 新增 // blog +1
			PressLike like= pressLikeRepository.save(pressLike);
			Blog blog = blogRepository.findById(like.getPostId()).orElse(null);
			blog.setComments(blog.getComments() + 1);
			blogRepository.save(blog);
			return 1;
		} else {
			// 移除 // blog -1 // Objects.equals(str1,str2)
			pressLikeRepository.delete(bool);
			Blog blog = blogRepository.findById(bool.getPostId()).orElse(null);
			blog.setComments(blog.getComments() - 1);
			blogRepository.save(blog);
			return -1;
		}
	}

	public String addFavorArticle(FavoriteArticle favor) {
		FavoriteArticle bool = favoriteArticleRepository.checkFavorArticle(favor.getPostId(), favor.getUserId());
		if (bool == null) {
			favoriteArticleRepository.save(favor);
			return "加入 文章收藏";
		} else {
			favoriteArticleRepository.delete(bool);
			return "移除 文章收藏";
		}
	}

}
