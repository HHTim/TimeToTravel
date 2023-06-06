package com.tibame.timetotravel.service.ServiceImpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.ArticleComment;
import com.tibame.timetotravel.entity.Blog;
import com.tibame.timetotravel.entity.Tags;
import com.tibame.timetotravel.repository.ArticleCommentRepository;
import com.tibame.timetotravel.repository.BlogRepository;
import com.tibame.timetotravel.repository.DefaultBlogRepository;
import com.tibame.timetotravel.view.DefaultBlogView;
import java.util.ArrayList;

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
	private PageBean<DefaultBlogView> pageBeanDefault  = new PageBean<>();

	// 拿ALL BLOG
	public List<Blog> findTestAll() {
		return blogRepository.findAll(); // 拿取dao的預設方法
	}

	// 拿ALL BLOG(view)
	public List<DefaultBlogView> defaultFindTestAll() {
		List<Tags> listTags = new ArrayList<Tags>();
		return defaultBlogRepository.findAllTest(); // 拿取dao的預設方法
	}

	// 單一 BLOG 資料
	public DefaultBlogView getBlogView(Integer postId) {
		// http://localhost:8080/BlogController/blog/1
//	public Blog getOneBlog(Integer postId) {
//		// https://stackoverflow.com/questions/33372588/jpa-the-column-name-dtype-was-not-found-in-this-resultset
//		return blogRepository.findByPostId(postId);
//	}
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

	// 只有 (畫面)上方條件搜尋
	public List<DefaultBlogView> serachTitleAndTags(String title, List<Integer> tagsId) {
		return defaultBlogRepository.findTitleAndTags(title, tagsId);
	}

	public PageBean<DefaultBlogView> searchOrderTimeTypePage(String title, List<Integer> tagsId, Integer postTypeId,
			Integer orderInt, Integer currPage, Integer rows) { // 參數待確認
		int start = (currPage - 1) * rows;
		int pageSize=0; // 你最後 會有多少頁
		// 篩選後符合的 // 每頁顯示筆數 // count 會暴掉 ???
		pageSize = (int) Math
				.ceil(defaultBlogRepository.searchCountTitleTags(title, tagsId, postTypeId).size() / (double) rows);
		//後面是OK 的
		// 設定// 符合條件的record// 給pageBean
		pageBeanDefault.setRows(
				defaultBlogRepository.searchTitleTagsOrderTypePage(title, tagsId, postTypeId, orderInt, start, rows));
		pageBeanDefault.setPageSize(Math.max(pageSize, 1));// 調整 頁數(校正 1頁狀況)
		return pageBeanDefault;
	}

}
