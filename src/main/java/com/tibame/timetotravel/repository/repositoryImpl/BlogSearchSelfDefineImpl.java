package com.tibame.timetotravel.repository.repositoryImpl;

import java.util.List;
import java.util.ArrayList;

import com.tibame.timetotravel.entity.Blog;
import com.tibame.timetotravel.entity.Tags;
import com.tibame.timetotravel.repository.BlogSearchSelfDefine;
import com.tibame.timetotravel.view.DefaultBlogView;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class BlogSearchSelfDefineImpl implements BlogSearchSelfDefine {

	@PersistenceContext
	private EntityManager entityManager;

//	SELECT B.* 
//	FROM BLOG B 
//	INNER JOIN ARTICLE_TAG T1 ON B.POST_ID = T1.POST_ID AND T1.TAG_ID = 1 
//	INNER JOIN ARTICLE_TAG T2 ON B.POST_ID = T2.POST_ID AND T2.TAG_ID = 2
//	where B.POST_TITLE like '%' ;
	@Override
	public List<DefaultBlogView> findTitleAndTags(String title, List<Integer> tags) {
		String dynamicTags = "";
		for (int i = 0; i < tags.size(); i++) {
			dynamicTags += "INNER JOIN ARTICLE_TAG T" + i + " ON B.POST_ID = T" + i + ".POST_ID AND T" + i
					+ ".TAG_ID =? ";
		}
		String sql = "SELECT B.* FROM DEFAULT_BLOG B " + dynamicTags + " where B.POST_TITLE like ? ;";
//        String sql = "SELECT B.* FROM BLOG B " + dynamicTags + " where B.POST_TITLE like ? ;";
//		Query query = entityManager.createNativeQuery(sql, Blog.class);
		Query query = entityManager.createNativeQuery(sql, DefaultBlogView.class);
		for (int i = 0; i < tags.size(); i++) {
			query.setParameter(i + 1, tags.get(i));
		}
		query.setParameter(tags.size() + 1, "%" + title + "%");
		return query.getResultList();
	}

	// SELECT B.*
	// FROM BLOG B
	// INNER JOIN ARTICLE_TAG T1 ON B.POST_ID = T1.POST_ID AND T1.TAG_ID = 7
	// #INNER JOIN ARTICLE_TAG T2 ON B.POST_ID = T2.POST_ID AND T2.TAG_ID = 2
	// where B.POST_TITLE like '%'
	// and B.post_type_id like "_"
	// #order by b.post_update_time desc,b.likes desc,b.comments desc
	// #order by b.likes desc,b.comments desc
	// order by b.comments desc,b.likes desc
	// limit 0,20;
	public List<DefaultBlogView> searchTitleTagsOrderTypePage(String title, List<Integer> tags, Integer postTypeId,
			Integer orderInt, Integer start, Integer pageSize) {
		String dynamicTags = "";
		for (int i = 0; i < tags.size(); i++) {
			dynamicTags += "INNER JOIN ARTICLE_TAG T" + i + " ON B.POST_ID = T" + i + ".POST_ID AND T" + i
					+ ".TAG_ID =? ";
		}
		String typeSql = "";
		int ifAll = 0;
		if (postTypeId == 7) { // 全部
			typeSql = " and B.post_type_id like \"_\" ";
			ifAll = -1;
		} else {
			typeSql = " and B.post_type_id like ? ";
		}
		String orderSql = "";
		switch (orderInt) {
		case 1:
			orderSql = " order by b.post_update_time desc,b.likes desc,b.comments desc ";
			break;
		case 2:
			orderSql = " order by b.likes desc,b.comments desc,b.post_update_time desc ";
			break;
		case 3:
			orderSql = " order by b.comments desc,b.likes desc,b.post_update_time desc ";
			break;
		}
		String sql = "SELECT B.* FROM DEFAULT_BLOG B " + dynamicTags + " where B.POST_TITLE like ? " + typeSql
				+ orderSql + " limit ? , ? ";
		// ---------------- 分水嶺 -----------------
		Query query = entityManager.createNativeQuery(sql, DefaultBlogView.class);
		for (int i = 0; i < tags.size(); i++) {
			query.setParameter(i + 1, tags.get(i));
		}
		query.setParameter(tags.size() + 1, "%" + title + "%");
		if (ifAll == 0) {
			query.setParameter(tags.size() + 2, postTypeId);
		} else {
//			query.setParameter(tags.size() + 2, "_");			
		}
		query.setParameter(tags.size() + ifAll + 3, start);
		query.setParameter(tags.size() + ifAll + 4, pageSize);
		return query.getResultList();
	}

	public List<DefaultBlogView> searchCountTitleTags(String title, List<Integer> tags, Integer postTypeId) {
		String dynamicTags = "";
		for (int i = 0; i < tags.size(); i++) {
			dynamicTags += "INNER JOIN ARTICLE_TAG T" + i + " ON B.POST_ID = T" + i + ".POST_ID AND T" + i
					+ ".TAG_ID =? ";
		}
		String typeSql = "";
		int ifAll = 0;
		if (postTypeId == 7) { // 全部
			typeSql = " and B.post_type_id like \"_\" ";
			ifAll = -1;
		} else {
			typeSql = " and B.post_type_id like ? ";
		}
		String sql = "SELECT B.* FROM DEFAULT_BLOG B " + dynamicTags + " where B.POST_TITLE like ? " + typeSql;
		// ---------------- 分水嶺 -----------------
		Query query = entityManager.createNativeQuery(sql, DefaultBlogView.class);
		for (int i = 0; i < tags.size(); i++) {
			query.setParameter(i + 1, tags.get(i));
		}
		query.setParameter(tags.size() + 1, "%" + title + "%");
		if (ifAll == 0) {
			query.setParameter(tags.size() + 2, postTypeId);
		} else {
//			query.setParameter(tags.size() + 2, "_");			
		}
		return query.getResultList();
//		System.out.println(sql);
//		return ((Number) query.getSingleResult()).intValue();
	}
}
