package com.tibame.timetotravel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	// 模糊搜尋
	
	// 分類查詢
	
	// 讚數排序 時間排序 前端???

	// 前兩者 結合 分頁功能
}
