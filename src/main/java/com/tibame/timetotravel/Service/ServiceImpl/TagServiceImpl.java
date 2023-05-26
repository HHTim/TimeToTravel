package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.entity.ArticleTags;
import com.tibame.timetotravel.entity.Tags;
import com.tibame.timetotravel.repository.ArticleTagsRepository;
import com.tibame.timetotravel.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("tagService")
public class TagServiceImpl {

	@Autowired
	@Qualifier("articleTagsRepository")
	private ArticleTagsRepository articleTagsRepository;

	@Autowired
	@Qualifier("tagsRepository")
	private TagsRepository tagsRepository;

	// private PageBean<ArticleTags> pageBean;

	// 取得 tagId 並 再抽取 tagId 的 標籤名
	public List<Tags> findArticleTags(Integer postId) {
		List<ArticleTags> getList = articleTagsRepository.findByPostId(postId);
		List<Tags> getTags = new ArrayList<Tags>();
		for (int i = 0; i < getList.size(); i++) {
			// getTags.add( tagsRepository.findById(getList.get(i).getTagId()) );
			Optional<Tags> tagsOptional = tagsRepository.findById(getList.get(i).getTagId());
			if (tagsOptional.isPresent()) {
				Tags tags = tagsOptional.get();
				getTags.add(tags);
			}
		}
		// SELECT a.* , t.* FROM ARTICLE_TAG a,tags t where a.Tag_ID=t.TAG_ID and POST_ID = 1;
		return getTags;
	}

}
