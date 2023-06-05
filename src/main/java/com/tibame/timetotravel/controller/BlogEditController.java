package com.tibame.timetotravel.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.timetotravel.entity.ArticleTags;
import com.tibame.timetotravel.entity.Blog;
import com.tibame.timetotravel.entity.Tags;
import com.tibame.timetotravel.repository.FavoriteArticleRepository;
import com.tibame.timetotravel.service.ServiceImpl.BlogServiceImpl;
import com.tibame.timetotravel.service.ServiceImpl.TagServiceImpl;
import com.tibame.timetotravel.view.DefaultBlogView;

@RestController
@RequestMapping("/BlogEditController")
public class BlogEditController {

	@Autowired
	@Qualifier("tagService")
	private TagServiceImpl tagServiceImpl;

	@Autowired
	@Qualifier("blogService")
	private BlogServiceImpl blogServiceImpl;

	//

	@PostMapping("/blog-edit/addTag/{postId}")
	public ArticleTags addTag(@PathVariable Integer postId, @RequestBody String tagName) {
		return tagServiceImpl.addTagInBlog(tagName, postId);
	}

	@DeleteMapping("/blog-edit/removeTag")
	public String removeTag(@RequestBody ArticleTags tag) {
		return tagServiceImpl.removeTagInBlog(tag);
	}

	@GetMapping("/blog-edit/{postId}")
	public DefaultBlogView getBlog(@PathVariable Integer postId) {
		return blogServiceImpl.getBlogView(postId);
	}

	// 更新文章 updata目前可 (新增 還未測試)
	@PostMapping("/blog-edit/update")
	public Integer saveBlog(@RequestBody Blog blog) {
		return blogServiceImpl.saveBlog(blog).getPostId();
	}

	// 更新文章標籤
	@PatchMapping("/blog-edit/addTags/{postId}")
	public List<ArticleTags> addTags(@RequestBody List<String> listTag, @PathVariable Integer postId) {
		return tagServiceImpl.addTagListInBlog(listTag, postId);
	}

}
