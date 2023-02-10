package com.powerfuljava.domain;

import java.util.Date;

import lombok.Data;

@Data
public class TravellReplyVO {
	private Long t_rno;
	private Long t_bno;
	
	private String reply;
	private String replyer;
	private Date replyDate;
	private Date updateDate;
}
