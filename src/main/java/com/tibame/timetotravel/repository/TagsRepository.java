package com.tibame.timetotravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tibame.timetotravel.entity.Tags;

@Repository("tagsRepository")
public interface TagsRepository extends JpaRepository<Tags, Integer>{

	
	List<Tags> findByTag(String tag);
}
