package com.tibame.timetotravel.entity;

import java.sql.Timestamp;

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
@Table(name= "ARTICLE_COMMENT")
public class ArticleComment {
	// 之後欄位可能還要檢查 !!!!!!!!!
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "COMMENT_NO")
		private Integer commentNo;		
//		  `COMMENT_NO` int NOT NULL AUTO_INCREMENT COMMENT '留言編號',
		
		@Column(name = "POST_ID")
		private Integer postId;
//		  `POST_ID` int NOT NULL COMMENT '文章編號',
		
		@Column(name = "USER_ID")
		private Integer userId;
//		  `USER_ID` int NOT NULL COMMENT '會員編號',
		
		
		@Column(name = "COMMENT_CONTENT")
		private String commentContent;
//		  `COMMENT_CONTENT` varchar(50) NOT NULL COMMENT '留言內容',
		
		@Column(name = "COMMENT_DATETIME")
		private Timestamp commentDatetime;
//		  `COMMENT_DATETIME` datetime NOT NULL DEFAULT (now()) COMMENT '留言時間',
		
}
