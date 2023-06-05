package com.tibame.timetotravel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "ARTICLE_TAG")
public class ArticleTags {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTICLE_TAG_NO")
	private Integer article_Tag_No;
//	 `ARTICLE_TAG_NO` int NOT NULL AUTO_INCREMENT COMMENT '文章標籤編號',
	
	@Column(name = "POST_ID")
	private Integer postId;
//	 `POST_ID` int NOT NULL COMMENT '文章編號',
	
	@Column(name = "TAG_ID")
	private Integer tagId;
//	 `TAG_ID` int NOT NULL COMMENT '標籤編號',
}
