package com.tibame.timetotravel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.timetotravel.entity.FavoriteArticle;
import com.tibame.timetotravel.service.ServiceImpl.BlogFavorServiceImpl;
import com.tibame.timetotravel.service.ServiceImpl.BlogServiceImpl;
import com.tibame.timetotravel.view.DefaultBlogView;

@RestController
@RequestMapping("/BlogFavorController")
public class BlogFavorController {

	@Autowired
	@Qualifier("blogFavorService")
	private BlogFavorServiceImpl blogFavorService;

	@GetMapping("/blog/favor/{userId}")
	public List<DefaultBlogView> getFavorBlog(@PathVariable Integer userId) {
		return blogFavorService.findArticleList(userId);
	}

//	// 收藏 //  BlogController 寫過
//	@PostMapping("/blog/favor")
//	public String favorArticle(@RequestBody FavoriteArticle favor) {
//		return blogService.addFavorArticle(favor);
//	}

}
