package com.tibame.timetotravel.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// 暫時沒用
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ArticleCommentDTO {
	Integer commentNo;
	Integer postId;
	Integer userId;
	String commentContent;
	Timestamp commentDatetime;
	String userName;
	public ArticleCommentDTO() {
    }
	public ArticleCommentDTO(Integer commentNo, Integer postId, Integer userId, String commentContent,
			Timestamp commentDatetime, String userName) {
		super();
		this.commentNo = commentNo;
		this.postId = postId;
		this.userId = userId;
		this.commentContent = commentContent;
		this.commentDatetime = commentDatetime;
		this.userName = userName;
	}
//	String userAvatar; // LONGTEXT mySql
//	byte[] userAvatar;

}
