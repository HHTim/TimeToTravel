package com.tibame.timetotravel.service.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tibame.timetotravel.entity.ArticleTags;
import com.tibame.timetotravel.entity.Tags;
import com.tibame.timetotravel.repository.ArticleTagsRepository;
import com.tibame.timetotravel.repository.TagsRepository;

@Service("tagService")
public class TagServiceImpl {

	@Autowired
	@Qualifier("articleTagsRepository")
	private ArticleTagsRepository articleTagsRepository;

	@Autowired
	@Qualifier("tagsRepository")
	private TagsRepository tagsRepository;

	// private PageBean<ArticleTags> pageBean;

	public String findCheckTag(String tagName) {
		List<Tags> bool = tagsRepository.findByTag(tagName);
		if (bool != null && !bool.isEmpty()) {
			int check = articleTagsRepository.countByTagId(bool.get(0).getTagId());
			if (check == 0) {
				return "此標籤已被建立，但未於任何文章使用";
			} else {
				return bool.get(0).getTagId() + " 此標籤已被建立，且有 "+check+" 筆文章有此標籤";
			}
		} else {
			return "該標籤不存在於任何文章";
		}
	}

	// 拿取 文章所持有的標籤 在找對應的標籤名稱並回傳 (含 tagId tag)
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
		// SELECT a.* , t.* FROM ARTICLE_TAG a,tags t where a.Tag_ID=t.TAG_ID and
		// POST_ID = 1;
		return getTags;
	}

	// 針對文章 所持有的全部標籤進行修改 (包刮 新增 移除 ，已存在的不做變動的判斷)
	public List<ArticleTags> addTagListInBlog(List<String> tagName, Integer postId) {
		List<ArticleTags> newTags = new ArrayList<>();
		List<ArticleTags> oldTags = articleTagsRepository.findByPostId(postId);
		// 增加標籤
		for (int i = 0; i < tagName.size(); i++) {
			List<Tags> bool = tagsRepository.findByTag(tagName.get(i));
			if (bool != null && !bool.isEmpty()) {
				// 存在 不新增 加入標籤(postId userId) // 網址 和 body // bool.get(0).getTagId() 只能為 get(0)
				// !!
				ArticleTags addOneTag = articleTagsRepository.findByPostIdAndTagId(postId, bool.get(0).getTagId());
				ArticleTags addTags = new ArticleTags();
				if (addOneTag == null) {
					addTags.setPostId(postId);
					addTags.setTagId(bool.get(0).getTagId());
					addOneTag = articleTagsRepository.save(addTags);
				}
				newTags.add(addOneTag);
			} else {
				// 沒有>> 新增(tagName) 加入標籤( postId userId)
				Tags tag = new Tags();
				tag.setTag(tagName.get(i));
				tag = tagsRepository.save(tag);
				ArticleTags addOneTag = new ArticleTags();
				addOneTag.setPostId(postId);
				addOneTag.setTagId(tag.getTagId());
				addOneTag = articleTagsRepository.save(addOneTag);
				newTags.add(addOneTag);
			}
		}

		// 移除標籤
		List<ArticleTags> deleteTags = new ArrayList<>();
		for (ArticleTags elementB : oldTags) {
			boolean found = false;
			for (ArticleTags elementA : newTags) {
				if (elementB.getArticle_Tag_No().equals(elementA.getArticle_Tag_No())) {
					found = true;
					break;
				}
			}
//		    for (ArticleTags elementB : listB) { // 此方式尚未測試過
//		        if (!listA.contains(elementB)) {
//		            listC.add(elementB);
//		        }
//		    }
			if (!found)
				deleteTags.add(elementB);

		}
		for (ArticleTags elementC : deleteTags) {
			articleTagsRepository.delete(elementC);
		}
		return newTags;
	}

	// 新增單一標籤
	public ArticleTags addTagInBlog(String tagName, Integer postId) {
		List<Tags> bool = tagsRepository.findByTag(tagName);
		System.out.println(bool);
		if (bool != null) {
			// 存在 不新增 加入標籤(postId userId) // 網址 和 body
			ArticleTags addOneTag = new ArticleTags();
			addOneTag.setPostId(postId);
			addOneTag.setTagId(bool.get(0).getTagId());
			articleTagsRepository.save(addOneTag);
			return addOneTag;
		} else {
			// 沒有>> 新增(tagName) 加入標籤( postId userId)
			Tags tag = new Tags();
			tag.setTag(tagName);
			tag = tagsRepository.save(tag);
			ArticleTags addOneTag = new ArticleTags();
			addOneTag.setPostId(postId);
			addOneTag.setTagId(tag.getTagId());
			articleTagsRepository.save(addOneTag);
			return addOneTag;
		}
	}

	// 移除單一標籤
	public String removeTagInBlog(ArticleTags tag) {
		articleTagsRepository.delete(tag);
		return "移除標籤完成";
	}

}
