package com.tibame.timetotravel.view;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import com.tibame.timetotravel.entity.Blog;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEFAULT_BLOG")
public class DefaultBlogView  {
//	public class DefaultBlogView extends Blog { // 此處會讓資料 BIND 成 null 或是 繼承後 要 已被 映射的就不用再次作映射
	// 繼承 這裡沒問題了 但是拿去 純 BLOG 時 誘導是 BLOG 欄未 多了一個 不存在的 dtype 或 冒出 此表的 LAST_COMMENT ??? 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POST_ID")
	private Integer postId;
	
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "POST_TITLE")
	private String postTitle;
	
	
	@Column(name = "POST_CONTENT")
	private String postContent;
	
	@Column(name = "POST_DATE")
	private Timestamp postDate;

	@Column(name = "LIKES")
	private Integer likes;
	
	@Column(name = "POST_PHOTO")
	private byte[] postPhoto;

	@Column(name = "POST_TYPE_ID")
	private Byte postTypeId;
//	整數類Byte ByteType TINYINT // hibernate 7-2

	@Column(name = "POST_UPDATE_TIME")
	private Timestamp postUpdateTime;

	@Column(name = "COMMENTS")
	private Integer comments;

	@Column(name = "LAST_COMMENT_USER_ID")
	private Integer lastCommentUserId;
	
	@Column(name = "LAST_COMMENT")
	private String lastComment;
	
	@Column(name = "LAST_COMMENT_DATETIME")
	private Timestamp lastCommentDatetime;
	
	@Column(name = "LAST_POST_TYPE")
	private String lastPostType;
	
	@Column(name= "USER_NAME")
	private String userName;
	
	@Column(name = "LAST_COMMENT_USER_NAME")
	private String lastCommentUserName;
	 
}
