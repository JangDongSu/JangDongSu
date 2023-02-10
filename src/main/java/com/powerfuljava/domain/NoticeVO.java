package com.powerfuljava.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class NoticeVO {
	private Long rn;
	private Long n_bno;
	private String n_title;
	private String n_content;
	private String n_writer;
	private Date regdate;
	private Date updatedate;
	private int viewCount;
	
	private List<NoticeAttachVO> attachList;
}
