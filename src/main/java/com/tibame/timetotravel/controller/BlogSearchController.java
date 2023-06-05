package com.tibame.timetotravel.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.Tags;
import com.tibame.timetotravel.service.ServiceImpl.BlogSearchServiceImpl;
import com.tibame.timetotravel.service.ServiceImpl.TagServiceImpl;
import com.tibame.timetotravel.view.DefaultBlogView;

@RestController
@RequestMapping("/BlogSearchController")
public class BlogSearchController {
	@Autowired
	@Qualifier("blogsService")
	private BlogSearchServiceImpl blogsService;
	@Autowired
	@Qualifier("tagService")
	private TagServiceImpl tagServiceImpl;
	// ========================= //

	// 拿所有BLOG
	// http://localhost:8080/BlogSearchController/default-blogs/all
	@GetMapping("/default-blogs/all")
	public List<DefaultBlogView> defaultFindAllBlogs() {
		return blogsService.defaultFindTestAll();
	}
	// SELECT b.* , a.POST_TYPE FROM blog b, article_type a where
	// b.POST_TYPE_ID=a.POST_TYPE_ID ;
	// http://localhost:8080/BlogSearchController/blogs/all // 暫時壞了
//	@GetMapping("/blogs/all")
//	public List<Blog> findAllBlogs(){
//		System.out.println("test all blogs");
//		System.out.println(blogsService.findTestAll());
//		return blogsService.findTestAll();
//	}

	// 文章的 標籤
	// http://localhost:8080/BlogSearchController/blog/tag/1
	@GetMapping("/blog/tag/{blogId}")
	public List<Tags> getArticleTags(@PathVariable Integer blogId) {
		return tagServiceImpl.findArticleTags(blogId);
	}

	// 標籤測試是否存在 並回傳標籤Id
	@GetMapping("/checkTag/{tagName}")
	public String checkTag(@PathVariable String tagName) {
		return tagServiceImpl.findCheckTag(tagName);
	}

	// 模糊搜尋 基礎
	@GetMapping("/search")
	public List<DefaultBlogView> search(@RequestParam("title") String title, @RequestParam("tags") List<Integer> tags) {
		return blogsService.serachTitleAndTags(title, tags);
	}

	// 模糊搜尋 基礎 + 排序選擇(時間更新 、 讚數 討論度? 做但前端看狀況) + 類型 + 分頁
	@GetMapping("/searchSQL")
	public PageBean<DefaultBlogView> searchOrderTimeTypePage(@RequestParam("title") String title,
			@RequestParam("tags") List<Integer> tags, @RequestParam("postType") Integer postTypeId,
			@RequestParam("orderType") Integer orderInt, @RequestParam("currPage") Integer currPage,
			@RequestParam("rows") Integer rows) {
		return blogsService.searchOrderTimeTypePage(title, tags, postTypeId, orderInt, currPage, rows);
	}

}
