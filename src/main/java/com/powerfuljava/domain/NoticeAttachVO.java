package com.powerfuljava.domain;

import lombok.Data;

@Data
public class NoticeAttachVO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	
	private Long n_bno;
	
}
