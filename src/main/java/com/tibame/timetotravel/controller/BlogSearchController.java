package com.tibame.timetotravel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.timetotravel.entity.Tags;
import com.tibame.timetotravel.service.ServiceImpl.BlogsServiceImpl;
import com.tibame.timetotravel.service.ServiceImpl.TagServiceImpl;
import com.tibame.timetotravel.view.DefaultBlogView;

@RestController
@RequestMapping("/BlogSearchController")
public class BlogSearchController {
	
	//blog (articleType member)
	//blogView
	//search tag check
	//articleTags (View)
	//articleComment(最新留言 userId)
	
	// 必要 blog 單一blog標籤 留言
	

	@Autowired
	@Qualifier("blogsService")
	private BlogsServiceImpl blogsService;
	@Autowired
	@Qualifier("tagService")
	private TagServiceImpl tagServiceImpl;
	// ========================= //
	
	// http://localhost:8080/BlogSearchController/default-blogs/all
	@GetMapping("/default-blogs/all")
	public List<DefaultBlogView> defaultFindAllBlogs(){
		return blogsService.defaultFindTestAll();
	}	
	// SELECT b.* , a.POST_TYPE FROM blog b, article_type a where b.POST_TYPE_ID=a.POST_TYPE_ID ;
	// http://localhost:8080/BlogSearchController/blogs/all // 暫時壞了
//	@GetMapping("/blogs/all")
//	public List<Blog> findAllBlogs(){
//		System.out.println("test all blogs");
//		System.out.println(blogsService.findTestAll());
//		return blogsService.findTestAll();
//	}
	
	// http://localhost:8080/BlogSearchController/blog/tag/1
	@GetMapping("/blog/tag/{blogId}")
	public List<Tags> getArticleTags(@PathVariable Integer blogId){
		return  tagServiceImpl.findArticleTags(blogId);
	}
	
}
