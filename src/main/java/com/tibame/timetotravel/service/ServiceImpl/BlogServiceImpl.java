package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.entity.ArticleComment;
import com.tibame.timetotravel.entity.Blog;
import com.tibame.timetotravel.entity.FavoriteArticle;
import com.tibame.timetotravel.entity.PressLike;
import com.tibame.timetotravel.repository.*;
import com.tibame.timetotravel.view.ArticleCommentView;
import com.tibame.timetotravel.view.DefaultBlogView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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
	@Qualifier("articleCommentViewRepository")
	private ArticleCommentViewRepository articleCommentViewRepository;
	@Autowired
	@Qualifier("pressLikeRepository")
	private PressLikeRepository pressLikeRepository;
	@Autowired
	@Qualifier("favoriteArticleRepository")
	private FavoriteArticleRepository favoriteArticleRepository;
	@Autowired
	@Qualifier("articleTagsRepository")
	private ArticleTagsRepository articleTagsRepository;

	// 新增 或 更新文章
	@Transactional
	public Blog saveBlog(Blog blog) {
		if (blog.getPostDate() == null) {
			blog.setPostDate(new Timestamp(System.currentTimeMillis()));
		}
		blog.setPostUpdateTime(new Timestamp(System.currentTimeMillis()));
		return blogRepository.save(blog);
	}

	// Blog 資料
	public DefaultBlogView getBlogView(Integer postId) {
		Optional<DefaultBlogView> blog = defaultBlogRepository.findById(postId);
		if (blog.isPresent()) {
			return (DefaultBlogView) blog.get();
		}
		return null;
	}

	// 取得 單一BLOG 所有留言
	public List<ArticleCommentView> findArticleComments(Integer postId) {
//		return articleCommentRepository.findByPostId(postId);
		return articleCommentViewRepository.findByPostId(postId);
	}
	@Transactional
	public int likeBlog(PressLike pressLike) {
		// 查 存在
		PressLike bool = pressLikeRepository.checkLike(pressLike.getPostId(), pressLike.getUserId());
		if (bool == null) {
			// 新增 // blog +1
			PressLike like = pressLikeRepository.save(pressLike);
			Blog blog = blogRepository.findById(like.getPostId()).orElse(null);
			blog.setLikes(blog.getLikes() + 1);
			blog = blogRepository.save(blog);
			return 1;
		} else {
			// 移除 // blog -1 // Objects.equals(str1,str2)
			pressLikeRepository.delete(bool);
			Blog blog = blogRepository.findById(bool.getPostId()).orElse(null);
			blog.setLikes(blog.getLikes() - 1);
			blogRepository.save(blog);
			return -1;
		}
	}
	@Transactional
	public String addFavorArticle(FavoriteArticle favor) {
		System.out.println(favor);
		FavoriteArticle bool = favoriteArticleRepository.checkFavorArticle(favor.getPostId(), favor.getUserId());
		if (bool == null) {
			favoriteArticleRepository.save(favor);
			return "已加入 文章收藏";
		} else {
			favoriteArticleRepository.delete(bool);
			return "已移除 文章收藏";
		}
	}

	//
	//
	// 刪除文章 (likes favor comment articleTags blog 5表 都要刪除)
	@Transactional
	public void deleteArticle(Integer postId) {
		// 刪除之後 1 若有被收藏後 將 看不到!!
		// 標籤的部分 !!! 若是他唯一 則再刪除!!! 之後有緣再做
		pressLikeRepository.deleteByPostId(postId);
		favoriteArticleRepository.deleteByPostId(postId);
		articleCommentRepository.deleteByPostId(postId);
		articleTagsRepository.deleteByPostId(postId); // 此處未包含 Tags 刪除 !!
		blogRepository.deleteById(postId);
	}

}
