package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.DefaultBlogView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("defaultBlogRepository")
public interface DefaultBlogRepository extends JpaRepository<DefaultBlogView, Integer>{	
	
    @Query(value = "SELECT * FROM default_blog",nativeQuery = true)
    List<DefaultBlogView> findAllTest(); // DefaultBlogView extends Blog !!
}
