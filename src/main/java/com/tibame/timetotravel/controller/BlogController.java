package com.tibame.timetotravel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.timetotravel.entity.ArticleComment;
import com.tibame.timetotravel.entity.Blog;
import com.tibame.timetotravel.entity.FavoriteArticle;
import com.tibame.timetotravel.entity.PressLike;
import com.tibame.timetotravel.entity.Tags;
import com.tibame.timetotravel.entity.User;
import com.tibame.timetotravel.service.ServiceImpl.ArticleCommentServiceImpl;
import com.tibame.timetotravel.service.ServiceImpl.BlogServiceImpl;
import com.tibame.timetotravel.service.ServiceImpl.TagServiceImpl;
import com.tibame.timetotravel.service.ServiceImpl.UserServiceImpl;
import com.tibame.timetotravel.view.ArticleCommentView;
import com.tibame.timetotravel.view.DefaultBlogView;

@RestController
@RequestMapping("/BlogController")
public class BlogController {
	@Autowired
	@Qualifier("blogService")
	private BlogServiceImpl blogService;

	@Autowired
	@Qualifier("tagService")
	private TagServiceImpl tagServiceImpl;

	@Autowired
	@Qualifier("articleCommentServiceImpl")
	private ArticleCommentServiceImpl articleCommentServiceImpl;
	@Autowired
	@Qualifier("UserService")
	private UserServiceImpl userServiceImpl;

	@GetMapping("/blog/getUser/{userId}")
	public String getBlogUser(@PathVariable Integer userId) {
		String gg= new String( userServiceImpl.findByUserId(userId).getUserAvatar() );
		System.out.println(gg);
		System.out.println("ddddddddddddddddddddd");
		return gg;
	}
	// 拿BLOG 資料
	@GetMapping("/blog/{postId}")
	public DefaultBlogView defaultGetBlogs(@PathVariable Integer postId) {
		return blogService.getBlogView(postId);
	}

	// 部落格標籤資料
	// http://localhost:8080/BlogController/blog-get-tags/1
	@GetMapping("/blog-get-tags/{postId}")
	public List<Tags> geTags(@PathVariable Integer postId) {
		return tagServiceImpl.findArticleTags(postId);
	}

	// 部落格所有留言
	// http://localhost:8080/BlogController/blog-comments/1
	@GetMapping("/blog-comments/{postId}")
	public List<ArticleCommentView> getComments(@PathVariable Integer postId) {
		return blogService.findArticleComments(postId);
	}

	// 新增留言
	@PostMapping("/comment/insert")
	public ArticleCommentView insert(@RequestBody ArticleComment comment) {
		// 增加 該文章 留言數 屬性 "insert 留言 "
		return articleCommentServiceImpl.insertComment(comment); // 要取得 他的id 到時候才能直接作修改
	}

	// 修改留言
	@PutMapping("/comment/update") // 待確認朝和的 annController
	public String update(@RequestBody ArticleComment comment) {
		return articleCommentServiceImpl.updateComment(comment);
	}

	// 刪除留言
	@DeleteMapping("/comment/delete/{postId}")
	public String delete(@RequestBody ArticleComment comment) {
		return articleCommentServiceImpl.deleteComment(comment);
	}

	// 按讚
	@PostMapping("/blog/like")
	public int like(@RequestBody PressLike like) {
		return blogService.likeBlog(like);
	}

	// 收藏
	@PostMapping("/blog/favor")
	public String favorArticle(@RequestBody FavoriteArticle favor) {
		return blogService.addFavorArticle(favor);
	}

	// 刪文
	@DeleteMapping("/blog/deleteBlog/{postId}")
	public void deleteArticle(@PathVariable Integer postId) {
		blogService.deleteArticle(postId);
	}
}
