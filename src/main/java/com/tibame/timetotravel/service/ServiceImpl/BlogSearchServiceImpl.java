package com.tibame.timetotravel.service.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.ArticleComment;
import com.tibame.timetotravel.entity.Blog;
import com.tibame.timetotravel.repository.ArticleCommentRepository;
import com.tibame.timetotravel.repository.BlogRepository;
import com.tibame.timetotravel.repository.DefaultBlogRepository;
import com.tibame.timetotravel.view.DefaultBlogView;

@Service("blogsService")
public class BlogSearchServiceImpl {

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
	private PageBean<Blog> pageBean;

	public List<Blog> findTestAll() {
		return blogRepository.findAll(); // 拿取dao的預設方法
	}

	public List<DefaultBlogView> defaultFindTestAll() {
		return defaultBlogRepository.findAllTest(); // 拿取dao的預設方法
	}

	// 單一 BLOG 資料
	// http://localhost:8080/BlogController/blog/1
//	public Blog getOneBlog(Integer postId) {
//		// https://stackoverflow.com/questions/33372588/jpa-the-column-name-dtype-was-not-found-in-this-resultset
//		return blogRepository.findByPostId(postId);
//	}
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

}
