package com.powerfuljava.domain;

import lombok.Data;

@Data
public class TravellAttachVO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	private String image;
	
	private Long t_bno;
}
