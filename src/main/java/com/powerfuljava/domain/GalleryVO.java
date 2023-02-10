package com.powerfuljava.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class GalleryVO {
	private Long rn;
	private Long p_bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
	
	private int replyCnt;
	
	private List<GalleryAttachVO> attachList;
}
