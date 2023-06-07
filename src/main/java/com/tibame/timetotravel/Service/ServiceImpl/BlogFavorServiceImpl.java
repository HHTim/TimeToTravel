package com.tibame.timetotravel.service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tibame.timetotravel.entity.Blog;
import com.tibame.timetotravel.entity.FavoriteArticle;
import com.tibame.timetotravel.repository.DefaultBlogRepository;
import com.tibame.timetotravel.repository.FavoriteArticleRepository;
import com.tibame.timetotravel.view.DefaultBlogView;

import java.util.ArrayList;

@Service("blogFavorService")
public class BlogFavorServiceImpl {

	@Autowired
	@Qualifier("favoriteArticleRepository")
	private FavoriteArticleRepository favoriteArticleRepository;
	@Autowired
	@Qualifier("defaultBlogRepository")
	private DefaultBlogRepository defaultBlogRepository;

	public List<DefaultBlogView> findArticleList(Integer userId) {
		// 查询收藏表的列表
		List<FavoriteArticle> favoriteArticles = favoriteArticleRepository.findByUserId(userId);
		// 创建一个用于存储Blog数据的列表
		List<DefaultBlogView> blogs = new ArrayList<>();
		// 遍历收藏列表中的每个FavoriteArticle对象
		for (FavoriteArticle favoriteArticle : favoriteArticles) {
			// 使用FavoriteArticle的postId属性查询对应的Blog数据
			DefaultBlogView blog = defaultBlogRepository.findById(favoriteArticle.getPostId()).orElse(null);
			// 如果找到了对应的Blog数据，则将其添加到结果列表中
			if (blog != null) {
				blogs.add(blog);
			}
		}
		// 返回包含Blog数据的列表
		return blogs;
	}

	@Transactional
	public String addRemoveFavorArticle(FavoriteArticle favor) {
		System.out.println(favor);
		FavoriteArticle bool = favoriteArticleRepository.checkFavorArticle(favor.getPostId(), favor.getUserId());
		// findByUserIdAndPostId 尚未測試
		if (bool == null) {
			favoriteArticleRepository.save(favor);
			return "已加入 文章收藏";
		} else {
			favoriteArticleRepository.delete(bool);
			return "已移除 文章收藏";
		}
	}

	// 分類

}
