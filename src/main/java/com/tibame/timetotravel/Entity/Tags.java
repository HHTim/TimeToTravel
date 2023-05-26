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
@Table(name= "TAGS")

public class Tags {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TAG_ID")
	private Integer tagId;
//	  `TAG_ID` int NOT NULL AUTO_INCREMENT COMMENT '標籤編號',

	@Column(name = "TAG")
	private String tag;
//	  `TAG` varchar(30) NOT NULL COMMENT '標籤',
	
}
