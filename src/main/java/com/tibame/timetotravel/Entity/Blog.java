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
@Table(name= "BLOG")
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POST_ID")
	private Integer postId;
//	  `POST_ID` int NOT NULL AUTO_INCREMENT COMMENT '文章編號',
	
	@Column(name = "USER_ID")
	private Integer userId;
//	  `USER_ID` int DEFAULT NULL COMMENT '會員編號',
	
	@Column(name = "POST_TITLE")
	private String postTitle;
//	  `POST_TITLE` varchar(50) NOT NULL COMMENT '文章標題',
	
	
	@Column(name = "POST_CONTENT")
	private String postContent;
//	  `POST_CONTENT` varchar(2500) NOT NULL COMMENT '文章內容',
	
	@Column(name = "POST_DATE")
	private Timestamp postDate;
//	  `POST_DATE` datetime NOT NULL DEFAULT (now()) COMMENT '發表時間',

	@Column(name = "LIKES")
	private Integer likes;
//	  `LIKES` int DEFAULT '0' COMMENT '按讚數',
	
	@Column(name = "POST_PHOTO")
	private byte[] postPhoto;
//	  `POST_PHOTO` longblob COMMENT '文章圖片',

	@Column(name = "POST_TYPE_ID")
	private Byte postTypeId;
//	整數類Byte ByteType TINYINT // hibernate 7-2
//	  `POST_TYPE_ID` tinyint NOT NULL COMMENT '文章類型編號',

	@Column(name = "POST_UPDATE_TIME")
	private Timestamp postUpdateTime;
//	  `POST_UPDATE_TIME` datetime DEFAULT NULL COMMENT '上次修改時間',

	@Column(name = "COMMENTS")
	private Integer comments;
//	  `COMMENTS` int DEFAULT '0' COMMENT '留言數',
	
}
