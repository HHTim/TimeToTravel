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
@Table(name= "FAVORITE_ARTICLE")
public class FavoriteArticle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FAVORITE_ARTICLE")
	private Integer favoriteArticle;
//	`FAVORITE_ARTICLE` int NOT NULL AUTO_INCREMENT COMMENT '文章收藏編號',
	
	@Column(name = "USER_ID")
	private Integer userId;
//	`USER_ID` int DEFAULT NULL COMMENT '會員編號',
	
	@Column(name = "POST_ID")
	private Integer postId;
//	`POST_ID` int NOT NULL COMMENT '文章編號',
}
