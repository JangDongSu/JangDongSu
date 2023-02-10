package com.powerfuljava.domain;

import java.util.Date;

import lombok.Data;

@Data
public class GalleryReplyVO {
	private Long p_rno;
	private Long p_bno;
	
	private String reply;
	private String replyer;
	private Date replyDate;
	private Date updateDate;
}
