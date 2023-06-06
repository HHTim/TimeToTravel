package com.tibame.timetotravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tibame.timetotravel.entity.Blog;
import com.tibame.timetotravel.entity.Tags;
import com.tibame.timetotravel.view.DefaultBlogView;

@Repository("blogSearchSelfDefine")
public interface BlogSearchSelfDefine {
	List<DefaultBlogView> findTitleAndTags(String title, List<Integer> tags);

	List<DefaultBlogView> searchTitleTagsOrderTypePage(String title, List<Integer> tags, Integer postTypeId,
			Integer orderInt, Integer start, Integer pageSize);

	List<DefaultBlogView> searchCountTitleTags(String title, List<Integer> tags, Integer postTypeId);
}
