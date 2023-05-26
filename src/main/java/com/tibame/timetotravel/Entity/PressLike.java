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
@Table(name= "PRESS_LIKE")
public class PressLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer likeNo;
//	  `LIKE_NO` int NOT NULL AUTO_INCREMENT COMMENT '按讚流水號',
	
	@Column(name = "USER_ID")
	private Integer userId;
//	  `USER_ID` int NOT NULL COMMENT '會員編號',
	
	@Column(name = "POST_ID")
	private Integer postId;
//	  `POST_ID` int NOT NULL COMMENT '文章編號',
}
