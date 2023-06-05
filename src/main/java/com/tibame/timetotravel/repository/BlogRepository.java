package com.tibame.timetotravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tibame.timetotravel.entity.ArticleTags;
import com.tibame.timetotravel.entity.Blog;

@Repository("blogRepository") // 與@Autowired @Qualifier("blogRepository")綁定
public interface BlogRepository extends JpaRepository<Blog, Integer> {

//	save(entity)：保存一個實體對象到資料庫。 (（insert）操作 更新（update）操作)
//	saveAll(entities)：保存多個實體對象到資料庫。
//	findById(id)：根據主鍵查詢一個實體對象。
//	findAll()：查詢所有的實體對象。
//	delete(entity)：刪除一個實體對象。
//	deleteById(id)：根據主鍵刪除一個實體對象。
//	count()：返回資料庫中實體對象的總數。
//	existsById(id)：根據主鍵判斷實體對象是否存在。
//	findAllById(ids)：根據一組主鍵查詢多個實體對象。
//	deleteAll()：刪除所有實體對象。
//	findAll(Pageable)：根據分頁參數查詢實體對象，支持分頁和排序功能。

//	@Query(value = "select * from default_blog ",nativeQuery = true)
//    List<DefaultBlogViewo> findAlltest();
	@Query(value = "SELECT POST_ID,USER_ID,POST_TITLE,POST_CONTENT,POST_DATE,LIKES,POST_PHOTO,POST_TYPE_ID,POST_UPDATE_TIME,COMMENTS FROM BLOG where POST_ID = ?1 ", nativeQuery = true)
	Blog findByPostId(Integer postId);

}
