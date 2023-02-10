package com.powerfuljava.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class TravellVO {
	private Long rn;
	private Long t_bno;
	private String title;
	private String content;
	private String writer;
	private String area;
	private String address;
	private String kind;
	private Date regdate;
	private Date updateDate;
	
	private int replyCnt;
	
	private List<TravellAttachVO> attachList;
}
