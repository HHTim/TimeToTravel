package com.tibame.timetotravel.service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.Blog;
import com.tibame.timetotravel.repository.BlogRepository;
import com.tibame.timetotravel.repository.DefaultBlogRepository;
import com.tibame.timetotravel.view.DefaultBlogView;

@Service("blogsService")
public class BlogsServiceImpl {
	
	@Autowired
	@Qualifier("blogRepository")
	private BlogRepository blogsRepository;
	
	@Autowired
	@Qualifier("defaultBlogRepository")
	private DefaultBlogRepository defaultBlogRepository;
	
	@Autowired
    private PageBean<Blog> pageBean;

	public List<Blog> findTestAll(){
		return blogsRepository.findAll(); // 拿取dao的預設方法
	}
	
	public List<DefaultBlogView> defaultFindTestAll(){
		System.out.println("222222222222");
		return defaultBlogRepository.findAllTest(); // 拿取dao的預設方法
	}
}
