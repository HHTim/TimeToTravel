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
@Table(name= "ARTICLE_TYPE")
public class ArticleType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POST_TYPE_ID")
	private Integer postTypeId;
//	`POST_TYPE_ID` tinyint NOT NULL AUTO_INCREMENT COMMENT '文章類型編號',
	
	@Column(name = "POST_TYPE")
	private String postType;
//	`POST_TYPE` varchar(30) NOT NULL COMMENT '文章類型',
}
