package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tagsRepository")
public interface TagsRepository extends JpaRepository<Tags, Integer>{

}
