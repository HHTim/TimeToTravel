package com.tibame.timetotravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tibame.timetotravel.view.DefaultBlogView;

@Repository("defaultBlogRepository")
public interface DefaultBlogRepository extends JpaRepository<DefaultBlogView, Integer>,BlogSearchSelfDefine {	
	
    @Query(value = "SELECT * FROM default_blog",nativeQuery = true)
    List<DefaultBlogView> findAllTest(); // DefaultBlogView extends Blog !!
}
