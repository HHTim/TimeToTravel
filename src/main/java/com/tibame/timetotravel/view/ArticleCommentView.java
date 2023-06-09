package com.tibame.timetotravel.view;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VIEW_ARTICLE_COMMENT")
public class ArticleCommentView {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMMENT_NO")
	private Integer commentNo;

	@Column(name = "POST_ID")
	private Integer postId;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "COMMENT_CONTENT")
	private String commentContent;

	@Column(name = "COMMENT_DATETIME")
	private Timestamp commentDatetime;
	// 抄 USER.java 資料欄位
	@Column(name = "USER_NAME", nullable = false)
	private String userName;

	@Column(name = "USER_AVATAR", columnDefinition = "LONGBLOB")
    @Lob
    private String userAvatar;

}
